package com.example.testA1.service;

import com.example.testA1.model.Logins;
import com.example.testA1.model.Postings;
import com.example.testA1.repository.LoginsRepository;
import com.example.testA1.repository.PostingsRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@Service
public class CSVService {

    private final LoginsRepository loginsRepository;
    private final PostingsRepository postingsRepository;

    @Autowired
    public CSVService(LoginsRepository loginsRepository, PostingsRepository postingsRepository) {
        this.loginsRepository = loginsRepository;
        this.postingsRepository = postingsRepository;
    }

    public void save(MultipartFile loginsFile, MultipartFile postingsFile) {
        try {
            Reader readerLoginsFile = new BufferedReader(new InputStreamReader(loginsFile.getInputStream()));

            CsvToBean csvToBeanLogins = new CsvToBeanBuilder(readerLoginsFile)
                    .withType(Logins.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<Logins> listLogins = csvToBeanLogins.parse();
            loginsRepository.saveAll(listLogins);

            Reader readerPostingsFile = new BufferedReader(new InputStreamReader(postingsFile.getInputStream()));
            CsvToBean csvToBeanPostings = new CsvToBeanBuilder(readerPostingsFile)
                    .withSeparator(';')
                    .withType(Postings.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<Postings> listPostings = csvToBeanPostings.parse();


            for (Postings posting : listPostings) {
                if (posting.getUserName() == null) {
                    continue;
                }
                for (Logins login : listLogins) {
                    if (posting.getUserName().equals(login.getAppAccountName()) &&
                            "True".equals(login.getIsActive())) {
                        posting.setAuthorizedDelivery(true);
                        break;
                    }
                }
            }
            postingsRepository.saveAll(listPostings);
        } catch (IOException e) {
            throw new RuntimeException("Error! " + e.getMessage());
        }
    }

    public File downloadPostings() throws IOException {

        List<Postings> listPostings = postingsRepository.findAll();
        File file = File.createTempFile("postings", ".cvs");
        BufferedWriter writer = getBufferedWriter(file, listPostings);

        writer.close();

        return file;
    }

    private static BufferedWriter getBufferedWriter(File file, List<Postings> listPostings) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        writer.write("Mat. Doc.; Item; Doc. Date; Pstng Date; Material Description;" +
                " Quantity; BUn; Amount LC; Crcy; User Name; is_authorized_delivery\n");

        for (Postings posting : listPostings) {
            String cvsObject = String.format("%s; %d; %s; %s; %s; %d; %s; %s; %s; %s; %b\n",
                    posting.getMathDoc(), posting.getItem(), posting.getDocDate(),
                    posting.getPstngDate(), posting.getMaterialDescription(),
                    posting.getQuantity(), posting.getBun(), posting.getAmountLC(),
                    posting.getCrcy(), posting.getUserName(), posting.isAuthorizedDelivery());
            writer.write(cvsObject);
        }
        return writer;
    }
}
