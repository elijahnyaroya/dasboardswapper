package com.crypsol.dashboard_swapper.DMC;

public class dashboardDMC {

    String DeletePhoneNumber;
    String AppID;
    String DashBoardID;
    String ownerPhone;
    String imagePath;
    String image;
    String Language;
    String showwheel_title;
    String ShowID;
    String payable;
    String Price;
    String PriceCurrency;
    String paid;
    String CommissionPercent;
    String DisplayOrder;
    String tileAvailability;
    String expirydate;
    String QueryResponseUpTo63;
    String CreatedUnixDate;
    String NoOfQuizzes;

    String payBillAccount;

    public String getPayBillAccount() {
        return payBillAccount;
    }

    public dashboardDMC(String deletePhoneNumber, String appID, String dashBoardID, String ownerPhone, String imagePath,
                        String image, String language, String showwheel_title, String showID,
                        String payable, String price, String priceCurrency, String paid,
                        String commissionPercent, String displayOrder, String tileAvailability, String expirydate,
                        String queryResponseUpTo63, String createdUnixDate, String noOfQuizzes, String payBillAccount) {
        DeletePhoneNumber = deletePhoneNumber;
        AppID = appID;
        DashBoardID = dashBoardID;
        this.ownerPhone = ownerPhone;
        this.imagePath = imagePath;
        this.image = image;
        Language = language;
        this.showwheel_title = showwheel_title;
        ShowID = showID;
        this.payable = payable;
        Price = price;
        PriceCurrency = priceCurrency;
        this.paid = paid;
        CommissionPercent = commissionPercent;
        DisplayOrder = displayOrder;
        this.tileAvailability = tileAvailability;
        this.expirydate = expirydate;
        QueryResponseUpTo63 = queryResponseUpTo63;
        CreatedUnixDate = createdUnixDate;
        NoOfQuizzes = noOfQuizzes;
        this.payBillAccount = payBillAccount;
    }



    public String getAppID() {
        return AppID;
    }
    public String getDashBoardID() {
        return DashBoardID;
    }

    public String getImage() {
        return image;
    }

    public String getLanguage() {
        return Language;
    }

    public String getShowwheel_title() {
        return showwheel_title;
    }

    public String getShowID() {
        return ShowID;
    }

    public String getPayable() {
        return payable;
    }

    public String getPrice() {
        return Price;
    }

    public String getPriceCurrency() {
        return PriceCurrency;
    }

    public String getPaid() {
        return paid;
    }

    public String getDeletePhoneNumber() {
        return DeletePhoneNumber;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getCommissionPercent() {
        return CommissionPercent;
    }

    public String getDisplayOrder() {
        return DisplayOrder;
    }

    public String getTileAvailability() {
        return tileAvailability;
    }

    public String getExpirydate() {
        return expirydate;
    }

    public String getQueryResponseUpTo63() {
        return QueryResponseUpTo63;
    }


    public String getCreatedUnixDate() {
        return CreatedUnixDate;
    }

    public String getNoOfQuizzes() {
        return NoOfQuizzes;
    }
}
