/*
 * 235150200111051 Khaelano Abroor Maulana
 * 235150200111057 Arza Marevi Bangun
 * 235150207111058 Muhammad Lutfi Aziz
 * 235150207111059 Nabiel Tatra Edy Firdaus
 */

package components.payment;

import java.time.LocalDate;

import components.user.*;

public class ShippingDiscount extends Promotion {

    public ShippingDiscount(
            String promoCode, 
            LocalDate promoStartDate, 
            LocalDate promoEndDate,
            double discountPercentage, 
            long maxDiscountVal,
            long minimumShippingFee
        ) {
        super(
            promoCode, 
            promoStartDate, 
            promoEndDate,
            discountPercentage,
            maxDiscountVal,
            minimumShippingFee
        );
    }

    @Override
    public boolean isCustomerEligible(Member member) {
        if (member.getMembershipDuration() > 30) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isMinimumPriceEligible(Order order) {
        return false;
    }

    @Override
    public boolean isShippingDiscountEligible(Order order) {
        if (order.getShippingFee() > super.getMinTranscTreshold()) {
            return true;
        }
        return false;
    }

    @Override
    public long calculateShippingDiscount(Order order) {
        if (isShippingDiscountEligible(order) == true) {
            double potongan = order.getShippingFee() * super.getPercentage();
            return (long) potongan;
        } else {
            return 0;
        }
    }

    @Override
    public long totalCashback(Order order) {
        return 0;
    }

    @Override
    public long totalDiscount(Order order) {
        return 0;
    }
}
