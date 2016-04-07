package hr.bm.web.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hr.bm.utils.MyStock;
import yahoofinance.Stock;

@Controller
@RequestMapping(value = { "/stock" })
public class SockController {

	@Autowired
	private MailSender mailSender;

	@RequestMapping(method = RequestMethod.GET)
	public String stockHome(Model model) throws IOException {
		return "stock/stockInputPage";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String getStock(Model model, String symbol, String goalPrice, String eMail) throws IOException {
		Stock stock = MyStock.getStock(symbol);
		model.addAttribute("stock", stock);
		model.addAttribute("goalPrice", goalPrice);
		model.addAttribute("eMail", eMail);

		// e-mail alert
		SimpleMailMessage crunchifyMsg = new SimpleMailMessage();
		crunchifyMsg.setFrom("branko2mlikota@gmail.com");
		crunchifyMsg.setTo(eMail);
		crunchifyMsg.setSubject("Stock Alert Engine");
		String msg = "Please don't answer on this mail!"
				+ "\n Stock symbol: " + symbol
				+ "\n Goal price: " + goalPrice
				+ "\n Current price: " + stock.getQuote().getPrice()
				+ "\n\n Evo uspio sam skinuti cijene s yahoo-a i automatski poslati na mail :-D ";
		crunchifyMsg.setText(msg);
		mailSender.send(crunchifyMsg);
		return "stock/stockSummary";
	}

}
