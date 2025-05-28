package org.usageTranslator.model;

public class Chargeable {
    private int partnerID;
    private String product;
    private String partnerPurchasedPlanID;
    private String plan;
    private int usage;

    public int getPartnerID() {
        return partnerID;
    }

    public void setPartnerID(int partnerID) {
        this.partnerID = partnerID;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getPartnerPurchasedPlanID() {
        return partnerPurchasedPlanID;
    }

    public void setPartnerPurchasedPlanID(String partnerPurchasedPlanID) {
        this.partnerPurchasedPlanID = partnerPurchasedPlanID;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public int getUsage() {
        return usage;
    }

    public void setUsage(int usage) {
        this.usage = usage;
    }
}
