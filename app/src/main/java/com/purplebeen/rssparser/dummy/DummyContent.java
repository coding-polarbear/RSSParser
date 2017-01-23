package com.purplebeen.rssparser.dummy;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.purplebeen.rssparser.ItemListActivity;
import com.purplebeen.rssparser.MainActivity;
import com.purplebeen.rssparser.Parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {
    private static ArrayList<String> titles;
    private static ArrayList<String> descriptions;
    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

            /**
             * A map of sample (dummy) items, by ID.
             */
            public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

            private static final int COUNT = 25;
        public static void loadData() {
            try {
                Parser parser = new Parser();
                parser.setURL(MainActivity.url);
                ArrayList[] array = parser.execute(null,null,null).get();
            titles = array[0];
            descriptions = array[1];
            for (int i = 0; i < titles.size(); i++) {
                Log.d("Test", "title = " + titles.get(i));
                addItem(new DummyItem(Integer.toString(i + 1), titles.get(i), descriptions.get(i)));
            }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    static {
        // Add some sample items.
        loadData();
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
            return builder.toString();
        }

        /**
         * A dummy item representing a piece of content.
         */
        public static class DummyItem {
            public final String id;
            public final String content;
            public final String details;

            public DummyItem(String id, String content, String details) {
                this.id = id;
                this.content = content;
                this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
