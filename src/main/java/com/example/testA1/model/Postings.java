package com.example.testA1.model;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "postings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Postings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "math_doc")
    @CsvBindByName(column = "Mat. Doc.")
    private String mathDoc;
    @Column(name = "item")
    @CsvBindByName(column = "Item")
    private int item;
    @Column(name = "doc_date")
    @CsvBindByName(column = "Doc. Date")
    private String docDate;
    @Column(name = "pstng_date")
    @CsvBindByName(column = "Pstng Date")
    private String pstngDate;
    @Column(name = "material_description")
    @CsvBindByName(column = "Material Description")
    private String materialDescription;
    @Column(name = "quantity")
    @CsvBindByName(column = "Quantity")
    private int quantity;
    @Column(name = "bun")
    @CsvBindByName(column = "BUn")
    private String bun;
    @Column(name = "amount_lc")
    @CsvBindByName(column = "Amount LC")
    private String amountLC;
    @Column(name = "crcy")
    @CsvBindByName(column = "Crcy")
    private String crcy;
    @Column(name = "user_name")
    @CsvBindByName(column = "User Name")
    private String userName;
    @Column(name = "is_authorized_delivery")
    private boolean isAuthorizedDelivery;
}
