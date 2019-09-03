package uniba.di.sms.ibtourapp.tourapp;

import android.content.Context;

import java.util.concurrent.ExecutionException;

public class Translate {
    static String Translate(String textToBeTranslated, String languagePair, Context context){
        TranslatorBackgroundTask translatorBackgroundTask= new TranslatorBackgroundTask(context);
        String translationResult = null;
        try {
            translationResult = translatorBackgroundTask.execute(textToBeTranslated,languagePair).get(); // Returns the translated text as a String
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return translationResult;
    }
}
