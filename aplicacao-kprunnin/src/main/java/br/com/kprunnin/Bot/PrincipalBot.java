package br.com.kprunnin.Bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class PrincipalBot {

	public static void main(String[] args) {
		ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new KPRunnin());

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
	}
}