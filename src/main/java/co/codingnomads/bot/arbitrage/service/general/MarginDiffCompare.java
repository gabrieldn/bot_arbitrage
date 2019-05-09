package co.codingnomads.bot.arbitrage.service.general;

import co.codingnomads.bot.arbitrage.model.ticker.TickerData;
import java.math.BigDecimal;
import java.math.MathContext;

/**
 * @author Kevin Neag
 */

/**
 * A Utill class for big decimal comparisons
 *
 */
public class MarginDiffCompare {


    /**
     * find the difference between the arbitrageMargin and the percent difference of the highBid and lowAsk
     * (((highBid - lowAsk)/highBid) * 100) - arbitrageMargin
     * @param lowAsk lowestAsk price
     * @param highBid highest BidPrice
     * @param arbitrageMargin percentage of returns you want to make
     * @return the difference between the arbitrage margin and percent difference highBid and lowAsk
     */
    public BigDecimal diffWithMargin(TickerData lowAsk, TickerData highBid, double arbitrageMargin) {
        return diffWithMargin(lowAsk.getAsk(), highBid.getBid(), arbitrageMargin);
    }
    public BigDecimal diffWithMargin(BigDecimal lowAsk, BigDecimal highBid, double arbitrageMargin) {
        MathContext mc = new MathContext(10);

        BigDecimal highBidMinusLowAsk = highBid.subtract(lowAsk);

        BigDecimal difference = highBidMinusLowAsk.divide(highBid, mc);

        BigDecimal diffPercent = difference.multiply(BigDecimal.valueOf(100));

        BigDecimal differenceOfMarginFormatted = BigDecimal.valueOf(arbitrageMargin);

        return diffPercent.subtract(differenceOfMarginFormatted);
    }


    /**
     * percent difference between highBid and lowAsk
     *(highBid - lowAsk)/highBid) * 100)
     * @param lowAsk lowest ask price
     * @param highBid highest bid price
     * @return percent difference highBid and lowAsk
     */
    public BigDecimal findDiff(TickerData lowAsk, TickerData highBid){
        return findDiff(lowAsk.getAsk(), highBid.getBid());
    }

    public BigDecimal findDiff(BigDecimal lowAsk, BigDecimal highBid) {
        MathContext mc = new MathContext(10);

        BigDecimal highBidMinusLowAsk = highBid.subtract(lowAsk);

        BigDecimal difference = highBidMinusLowAsk.divide(highBid, mc);

        return difference.multiply(BigDecimal.valueOf(100));
    }
}
