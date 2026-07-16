package com.library.dto;

public class DashboardStatsDTO {

    private int totalBooks;
    private int totalCopies;
    private int availableCopies;
    private int borrowedCopies;
    private int totalMembers;
    private int totalLibrarians;
    private int overdueBooks;

    //---------- GETTERS & SETTERS ----------
    public int getTotalBooks() {
        return totalBooks;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public int getBorrowedCopies() {
        return borrowedCopies;
    }

    public int getTotalMembers() {
        return totalMembers;
    }

    public int getTotalLibrarians() {
        return totalLibrarians;
    }

    public int getOverdueBooks() {
        return overdueBooks;
    }

    public void setTotalBooks(int totalBooks) {
        this.totalBooks = totalBooks;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }
    
    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public void setBorrowedCopies(int borrowedCopies) {
        this.borrowedCopies = borrowedCopies;
    }

    public void setTotalMembers(int totalMembers) {
        this.totalMembers = totalMembers;
    }

    public void setTotalLibrarians(int totalLibrarians) {
        this.totalLibrarians = totalLibrarians;
    }

    public void setOverdueBooks(int overdueBooks) {
        this.overdueBooks = overdueBooks;
    }

}//end class DashboardStatsDTO