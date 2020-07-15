package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class BotClient extends Client {

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        int x = (int) (Math.random() * 100);

        return "date_bot_" + x;
    }

    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }

    public class BotSocketThread extends Client.SocketThread{
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);

            if (message.split(":").length==2) {
                String[] arr = message.split(":");
                String userMessage = arr[1];

                SimpleDateFormat format;

                switch (message.substring(message.indexOf(":") + 2)) {
                    case "дата":
                        format = new SimpleDateFormat("d.MM.YYYY");
                        break;
                    case "день":
                        format = new SimpleDateFormat("d");
                        break;
                    case "месяц":
                        format = new SimpleDateFormat("MMMM");
                        break;
                    case "год":
                        format = new SimpleDateFormat("YYYY");
                        break;
                    case "время":
                        format = new SimpleDateFormat("H:mm:ss");
                        break;
                    case "час":
                        format = new SimpleDateFormat("H");
                        break;
                    case "минуты":
                        format = new SimpleDateFormat("m");
                        break;
                    case "секунды":
                        format = new SimpleDateFormat("s");
                        break;
                    default:
                        format = null;
                }
                if (format != null) {
                    sendTextMessage("Информация для " + message.substring(0, message.indexOf(':')) + ": " + format.format(Calendar.getInstance().getTime()));
                }

            }
        }


    }
}
