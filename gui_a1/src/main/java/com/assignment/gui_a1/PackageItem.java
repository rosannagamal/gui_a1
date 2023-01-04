package com.assignment.gui_a1;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PackageItem<E> {
    private String packagingDate;
    @Getter
    @Setter
    private Packaging packageType;
    @Getter @Setter private E item;
    @Getter private Triplet<E, Packaging, String> packagedElement;

    public String getPackagingDate() {
        return packagingDate;
    }

    public void setPackagingDate(DateTimeFormatter packagingDate) {
        LocalDateTime dateTimeObj = LocalDateTime.now();
        DateTimeFormatter formatting = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        this.packagingDate = dateTimeObj.format(formatting);
    }

    public void setPackagedElement() {
        this.packagedElement = Triplet.of(getItem(), getPackageType(), getPackagingDate());
    }

    public PackageItem(Packaging packageType, E item) {
        this.packageType = packageType;
        this.item = item;
    }
}

