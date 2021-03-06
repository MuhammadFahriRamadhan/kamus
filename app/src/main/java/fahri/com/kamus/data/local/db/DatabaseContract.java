package fahri.com.kamus.data.local.db;

import android.provider.BaseColumns;

public class DatabaseContract {

    static String TABLE_NAME_ENG_IND = "table_kamus_engind";
    static String TABLE_NAME_IND_ENG = "table_kamus_indeng";

    static final class KamusColumnsEngInd implements BaseColumns {

        static String SEARCH_WORD_ENG_IND = "search_word_engind";

        static String RESULT_WORD_ENG_IND = "result_word_engind";
    }

    static final class KamusColumnsIndEng implements BaseColumns {

        static String SEARCH_WORD_IND_ENG = "search_word_indeng";

        static String RESULT_WORD_IND_ENG = "result_word_indeng";
    }
}
