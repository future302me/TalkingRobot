package com.gui.royal.youtalking.util;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;
import com.gui.royal.youtalking.db.ContentDB;
import com.gui.royal.youtalking.model.Texts;

/**解析服务器返回的JSON数据
 * {"code":100000,"text":"很高兴和你聊天"}
 * Created by Jeremy on 2015/5/27.
 */
public class Utility {
    public static String handleTextsResponse(Context context, String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            String code = jsonObject.getString("code");
            String content = jsonObject.getString("text");
            Texts texts = new Texts();
            texts.setCode(code);
            texts.setContent(content);
            ContentDB db = ContentDB.getInstance(context);
            db.saveReceivedTexts(texts);
            return content;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


}
