package br.com.kprunnin.Bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class KPRunnin extends TelegramLongPollingBot {

	public void onUpdateReceived(Update update) {
		System.out.println(update.getMessage().getText());
		//System.out.println(update.getMessage().getFrom().getFirstName());
		
		String command = update.getMessage().getText();
		SendMessage mensagem1 = new SendMessage();
		SendMessage mensagem2 = new SendMessage();
		
		if(command.equals("/start")) {
			mensagem1.setText("Olá, eu sou o KPRunnin', o que eu posso fazer por você?");
		}
		
		if(command.equals("/nome")) {
			System.out.println(update.getMessage().getFrom().getFirstName());
			mensagem1.setText(update.getMessage().getFrom().getFirstName());
		}
		
		if(command.equals("/sobrenome")) {
			System.out.println(update.getMessage().getFrom().getLastName());
			mensagem1.setText(update.getMessage().getFrom().getLastName());
			if(update.getMessage().getFrom().getLastName() == null) {
				mensagem1.setText("Você não tem um sobrenome");
				mensagem2.setText("Para adicionar um, vá em configurações > canto superior direito > editar nome");
			}
		}
		
		mensagem1.setChatId(update.getMessage().getChatId());
		mensagem2.setChatId(update.getMessage().getChatId());
		
		try {
			execute(mensagem1);
			execute(mensagem2);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}	
	}

	
	public String getBotUsername() {
		return "KPRurrin";
	}

	public String getBotToken() {
		return "1201909961:AAH718WMcx0BV8WC_Q-vI48VK5Spf2AVtr8";
	}
}