package magnum;

public interface MessageListener {
    void onMessage(PricingDataOuterClass.PricingData pricingData);
}
