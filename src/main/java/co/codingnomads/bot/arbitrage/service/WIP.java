package co.codingnomads.bot.arbitrage.service;

import co.codingnomads.bot.arbitrage.model.ActivatedExchange;
import co.codingnomads.bot.arbitrage.model.ArbitrageActionSelection;
import co.codingnomads.bot.arbitrage.model.BidAsk;
import co.codingnomads.bot.arbitrage.model.ExchangeDetailsEnum;
import org.knowm.xchange.currency.CurrencyPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * Created by Thomas Leruth on 12/14/17
 */

@Service
public class WIP {

//    @Autowired
//    DataUtil dataUtil;

    // todo Email action (Kevin later)
    // todo trade action (Thomas)
    // todo make this method running every X minutes (Kevin)
    // todo fix the issue with loger (ryan)
    // todo fix the issue with autowired (ryan)

    // Margin at which we take the risk of running the arbitrage it covers
    // 1) Fee (which I can't seem to be able to pull from API, somebody?) (check Kevin and hack the code)
    // 2) Delay leading to movement in bid/ask spread
    /**
     * A trading arbitrage bot
     * @param currencyPair the pair selected
     * @param selectedExchanges an ArrayList of ExchangeEnum has to be added
     * @param arbitrageMargin the margin in which arbitrage is accepted (cove the trading fee and delay leading to mov in bid/ask
     * @param arbitrageActionSelection Pojo including 3 booleans for the potential outcomes
     * @throws IOException
     */
    public void test(CurrencyPair currencyPair,
                     ArrayList<ExchangeDetailsEnum> selectedExchanges,
                     double arbitrageMargin,
                     ArbitrageActionSelection arbitrageActionSelection) throws IOException {

        ExchangeGetter exchangeGetter = new ExchangeGetter();

        // wondering if a hashmap would be more suitable
        // get all services for all selected exchanges
        ArrayList<ActivatedExchange> activatedExchanges = exchangeGetter.getAllSelectedExchangeServices(selectedExchanges);

        // todo get a method checking if enough fund (using currencypair.counter and .base) and desactivating exchange if not
        // todo only checking if exchange is not desactivated yet
        // todo send a message saying exchange is desactivated

        // todo autowire it
        ExchangeDataGetter exchangeDataGetter = new ExchangeDataGetter();

        // get the bid ask for all exchanges
        ArrayList<BidAsk> listBidAsk = exchangeDataGetter.getAllBidAsk(activatedExchanges, currencyPair);

        // todo handle with a custom exception on the getAllBidAsk
        if (listBidAsk.size() == 0) {
            System.out.println("This pair is not traded on the exchange selected");
            return;
        }

        // todo autowire it
        DataUtil dataUtil = new DataUtil();

        BidAsk lowAsk = dataUtil.lowAskFinder(listBidAsk);
        BidAsk highBid = dataUtil.highBidFinder(listBidAsk);

        BigDecimal difference = highBid.getBid().divide(lowAsk.getAsk(), 10, RoundingMode.HALF_UP);

        // todo autowire it
        ArbitrageAction arbitrageAction = new ArbitrageAction();

        if (arbitrageActionSelection.isPrintActionFlag()) {
            arbitrageAction.print(lowAsk, highBid, difference, arbitrageMargin);
        }
        if (arbitrageActionSelection.isEmailActionFlag()) {
            arbitrageAction.email();
        }
        if (arbitrageActionSelection.isTradeActionFlag()) {
            arbitrageAction.trade();
        }
    }
}