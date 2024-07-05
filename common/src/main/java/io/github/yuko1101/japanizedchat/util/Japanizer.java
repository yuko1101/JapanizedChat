package io.github.yuko1101.japanizedchat.util;

import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Japanizer {

    @Nullable
    public static String convert(String msg) {
        return conv(romaToKana(msg));
    }

    public static boolean hasFullWidth(String msg) {
        return Arrays.stream(msg.split("")).anyMatch(c -> c.getBytes().length > 1);
    }

    public static String romaToKana(String romaji) {
        romaji = romaji.replaceAll("nn", "ん");
        romaji = romaji.replaceAll("wwha", "っうぁ");
        romaji = romaji.replaceAll("wwhi", "っうぃ");
        romaji = romaji.replaceAll("wwhu", "っう");
        romaji = romaji.replaceAll("wwhe", "っうぇ");
        romaji = romaji.replaceAll("wwho", "っうぉ");
        romaji = romaji.replaceAll("wwyi", "っゐ");
        romaji = romaji.replaceAll("wwye", "っゑ");
        romaji = romaji.replaceAll("xxa", "っぁ");
        romaji = romaji.replaceAll("xxi", "っぃ");
        romaji = romaji.replaceAll("xxu", "っぅ");
        romaji = romaji.replaceAll("xxe", "っぇ");
        romaji = romaji.replaceAll("xxo", "っぉ");
        romaji = romaji.replaceAll("xxya", "っゃ");
        romaji = romaji.replaceAll("xxyu", "っゅ");
        romaji = romaji.replaceAll("xxyo", "っょ");
        romaji = romaji.replaceAll("xxtu", "っっ");
        romaji = romaji.replaceAll("lla", "っぁ");
        romaji = romaji.replaceAll("lli", "っぃ");
        romaji = romaji.replaceAll("llu", "っぅ");
        romaji = romaji.replaceAll("lle", "っぇ");
        romaji = romaji.replaceAll("llo", "っぉ");
        romaji = romaji.replaceAll("llya", "っゃ");
        romaji = romaji.replaceAll("llyu", "っゅ");
        romaji = romaji.replaceAll("llyo", "っょ");
        romaji = romaji.replaceAll("lltu", "っっ");
        romaji = romaji.replaceAll("kka", "っか");
        romaji = romaji.replaceAll("kki", "っき");
        romaji = romaji.replaceAll("kku", "っく");
        romaji = romaji.replaceAll("kke", "っけ");
        romaji = romaji.replaceAll("kko", "っこ");
        romaji = romaji.replaceAll("kkya", "っきゃ");
        romaji = romaji.replaceAll("kkyi", "っきぃ");
        romaji = romaji.replaceAll("kkyu", "っきゅ");
        romaji = romaji.replaceAll("kkye", "っきぇ");
        romaji = romaji.replaceAll("kkyo", "っきょ");
        romaji = romaji.replaceAll("gga", "っが");
        romaji = romaji.replaceAll("ggi", "っぎ");
        romaji = romaji.replaceAll("ggu", "っぐ");
        romaji = romaji.replaceAll("gge", "っげ");
        romaji = romaji.replaceAll("ggo", "っご");
        romaji = romaji.replaceAll("ggya", "っぎゃ");
        romaji = romaji.replaceAll("ggyi", "っぎぃ");
        romaji = romaji.replaceAll("ggyu", "っぎゅ");
        romaji = romaji.replaceAll("ggye", "っぎぇ");
        romaji = romaji.replaceAll("ggyo", "っぎょ");
        romaji = romaji.replaceAll("ttsa", "っつぁ");
        romaji = romaji.replaceAll("ttsi", "っつぃ");
        romaji = romaji.replaceAll("ttsu", "っつ");
        romaji = romaji.replaceAll("ttse", "っつぇ");
        romaji = romaji.replaceAll("ttso", "っつぉ");
        romaji = romaji.replaceAll("ssa", "っさ");
        romaji = romaji.replaceAll("ssi", "っし");
        romaji = romaji.replaceAll("ssu", "っす");
        romaji = romaji.replaceAll("sse", "っせ");
        romaji = romaji.replaceAll("sso", "っそ");
        romaji = romaji.replaceAll("cca", "っか");
        romaji = romaji.replaceAll("cci", "っし");
        romaji = romaji.replaceAll("ccu", "っく");
        romaji = romaji.replaceAll("cce", "っせ");
        romaji = romaji.replaceAll("cco", "っこ");
        romaji = romaji.replaceAll("ssha", "っしゃ");
        romaji = romaji.replaceAll("sshi", "っし");
        romaji = romaji.replaceAll("sshu", "っしゅ");
        romaji = romaji.replaceAll("sshe", "っしぇ");
        romaji = romaji.replaceAll("ssho", "っしょ");
        romaji = romaji.replaceAll("ssya", "っしゃ");
        romaji = romaji.replaceAll("ssyi", "っしぃ");
        romaji = romaji.replaceAll("ssyu", "っしゅ");
        romaji = romaji.replaceAll("ssye", "っしぇ");
        romaji = romaji.replaceAll("ssyo", "っしょ");
        romaji = romaji.replaceAll("zza", "っざ");
        romaji = romaji.replaceAll("zzi", "っじ");
        romaji = romaji.replaceAll("zzu", "っず");
        romaji = romaji.replaceAll("zze", "っぜ");
        romaji = romaji.replaceAll("zzo", "っぞ");
        romaji = romaji.replaceAll("jja", "っじゃ");
        romaji = romaji.replaceAll("jji", "っじ");
        romaji = romaji.replaceAll("jju", "っじゅ");
        romaji = romaji.replaceAll("jje", "っじぇ");
        romaji = romaji.replaceAll("jjo", "っじょ");
        romaji = romaji.replaceAll("jjya", "っじゃ");
        romaji = romaji.replaceAll("jjyi", "っじぃ");
        romaji = romaji.replaceAll("jjyu", "っじゅ");
        romaji = romaji.replaceAll("jjye", "っじぇ");
        romaji = romaji.replaceAll("jjyo", "っじょ");
        romaji = romaji.replaceAll("zzya", "っじゃ");
        romaji = romaji.replaceAll("zzyi", "っじぃ");
        romaji = romaji.replaceAll("zzyu", "っじゅ");
        romaji = romaji.replaceAll("zzye", "っじぇ");
        romaji = romaji.replaceAll("zzyo", "っじょ");
        romaji = romaji.replaceAll("tta", "った");
        romaji = romaji.replaceAll("tti", "っち");
        romaji = romaji.replaceAll("ttu", "っつ");
        romaji = romaji.replaceAll("tte", "って");
        romaji = romaji.replaceAll("tto", "っと");
        romaji = romaji.replaceAll("ttya", "っちゃ");
        romaji = romaji.replaceAll("ttyi", "っちぃ");
        romaji = romaji.replaceAll("ttyu", "っちゅ");
        romaji = romaji.replaceAll("ttye", "っちぇ");
        romaji = romaji.replaceAll("ttyo", "っちょ");
        romaji = romaji.replaceAll("ttha", "ってゃ");
        romaji = romaji.replaceAll("tthi", "ってぃ");
        romaji = romaji.replaceAll("tthu", "ってゅ");
        romaji = romaji.replaceAll("tthe", "ってぇ");
        romaji = romaji.replaceAll("ttho", "ってょ");
        romaji = romaji.replaceAll("ccya", "っちゃ");
        romaji = romaji.replaceAll("ccyi", "っちぃ");
        romaji = romaji.replaceAll("ccyu", "っちゅ");
        romaji = romaji.replaceAll("ccye", "っちぇ");
        romaji = romaji.replaceAll("ccyo", "っちょ");
        romaji = romaji.replaceAll("ccha", "っちゃ");
        romaji = romaji.replaceAll("cchi", "っち");
        romaji = romaji.replaceAll("cchu", "っちゅ");
        romaji = romaji.replaceAll("cche", "っちぇ");
        romaji = romaji.replaceAll("ccho", "っちょ");
        romaji = romaji.replaceAll("dda", "っだ");
        romaji = romaji.replaceAll("ddi", "っぢ");
        romaji = romaji.replaceAll("ddu", "っづ");
        romaji = romaji.replaceAll("dde", "っで");
        romaji = romaji.replaceAll("ddo", "っど");
        romaji = romaji.replaceAll("ddya", "っぢゃ");
        romaji = romaji.replaceAll("ddyi", "っぢぃ");
        romaji = romaji.replaceAll("ddyu", "っぢゅ");
        romaji = romaji.replaceAll("ddye", "っぢぇ");
        romaji = romaji.replaceAll("ddyo", "っぢょ");
        romaji = romaji.replaceAll("ddha", "っでゃ");
        romaji = romaji.replaceAll("ddhi", "っでぃ");
        romaji = romaji.replaceAll("ddhu", "っでゅ");
        romaji = romaji.replaceAll("ddhe", "っでぇ");
        romaji = romaji.replaceAll("ddho", "っでょ");
        romaji = romaji.replaceAll("ppha", "っふぁ");
        romaji = romaji.replaceAll("pphi", "っふぃ");
        romaji = romaji.replaceAll("pphu", "っふ");
        romaji = romaji.replaceAll("pphe", "っふぇ");
        romaji = romaji.replaceAll("ppho", "っふぉ");
        romaji = romaji.replaceAll("hha", "っは");
        romaji = romaji.replaceAll("hhi", "っひ");
        romaji = romaji.replaceAll("hhu", "っふ");
        romaji = romaji.replaceAll("hhe", "っへ");
        romaji = romaji.replaceAll("hho", "っほ");
        romaji = romaji.replaceAll("hhya", "っひゃ");
        romaji = romaji.replaceAll("hhyi", "っひぃ");
        romaji = romaji.replaceAll("hhyu", "っひゅ");
        romaji = romaji.replaceAll("hhye", "っひぇ");
        romaji = romaji.replaceAll("hhyo", "っひょ");
        romaji = romaji.replaceAll("ffa", "っふぁ");
        romaji = romaji.replaceAll("ffi", "っふぃ");
        romaji = romaji.replaceAll("ffu", "っふ");
        romaji = romaji.replaceAll("ffe", "っふぇ");
        romaji = romaji.replaceAll("ffo", "っふぉ");
        romaji = romaji.replaceAll("ppa", "っぱ");
        romaji = romaji.replaceAll("ppi", "っぴ");
        romaji = romaji.replaceAll("ppu", "っぷ");
        romaji = romaji.replaceAll("ppe", "っぺ");
        romaji = romaji.replaceAll("ppo", "っぽ");
        romaji = romaji.replaceAll("ppya", "っぴゃ");
        romaji = romaji.replaceAll("ppyi", "っぴぃ");
        romaji = romaji.replaceAll("ppyu", "っぴゅ");
        romaji = romaji.replaceAll("ppye", "っぴぇ");
        romaji = romaji.replaceAll("ppyo", "っぴょ");
        romaji = romaji.replaceAll("bba", "っば");
        romaji = romaji.replaceAll("bbi", "っび");
        romaji = romaji.replaceAll("bbu", "っぶ");
        romaji = romaji.replaceAll("bbe", "っべ");
        romaji = romaji.replaceAll("bbo", "っぼ");
        romaji = romaji.replaceAll("bbya", "っびゃ");
        romaji = romaji.replaceAll("bbyi", "っびぃ");
        romaji = romaji.replaceAll("bbyu", "っびゅ");
        romaji = romaji.replaceAll("bbye", "っびぇ");
        romaji = romaji.replaceAll("bbyo", "っびょ");
        romaji = romaji.replaceAll("vva", "っヴァ");
        romaji = romaji.replaceAll("vvi", "っヴィ");
        romaji = romaji.replaceAll("vvu", "っヴ");
        romaji = romaji.replaceAll("vve", "っヴェ");
        romaji = romaji.replaceAll("vvo", "っヴォ");
        romaji = romaji.replaceAll("vvya", "っヴャ");
        romaji = romaji.replaceAll("vvyu", "っヴュ");
        romaji = romaji.replaceAll("vvyo", "っヴョ");
        romaji = romaji.replaceAll("mma", "っま");
        romaji = romaji.replaceAll("mmi", "っみ");
        romaji = romaji.replaceAll("mmu", "っむ");
        romaji = romaji.replaceAll("mme", "っめ");
        romaji = romaji.replaceAll("mmo", "っも");
        romaji = romaji.replaceAll("mmya", "っみゃ");
        romaji = romaji.replaceAll("mmyi", "っみぃ");
        romaji = romaji.replaceAll("mmyu", "っみゅ");
        romaji = romaji.replaceAll("mmye", "っみぇ");
        romaji = romaji.replaceAll("mmyo", "っみょ");
        romaji = romaji.replaceAll("rrya", "っりゃ");
        romaji = romaji.replaceAll("rryi", "っりぃ");
        romaji = romaji.replaceAll("rryu", "っりゅ");
        romaji = romaji.replaceAll("rrye", "っりぇ");
        romaji = romaji.replaceAll("rryo", "っりょ");
        romaji = romaji.replaceAll("yya", "っや");
        romaji = romaji.replaceAll("yyu", "っゆ");
        romaji = romaji.replaceAll("yye", "っいぇ");
        romaji = romaji.replaceAll("yyo", "っよ");
        romaji = romaji.replaceAll("rra", "っら");
        romaji = romaji.replaceAll("rri", "っり");
        romaji = romaji.replaceAll("rru", "っる");
        romaji = romaji.replaceAll("rre", "っれ");
        romaji = romaji.replaceAll("rro", "っろ");
        romaji = romaji.replaceAll("wwa", "っわ");
        romaji = romaji.replaceAll("wwi", "っうぃ");
        romaji = romaji.replaceAll("wwu", "っう");
        romaji = romaji.replaceAll("wwe", "っうぇ");
        romaji = romaji.replaceAll("wwo", "っを");
        romaji = romaji.replaceAll("wha", "うぁ");
        romaji = romaji.replaceAll("whi", "うぃ");
        romaji = romaji.replaceAll("whu", "う");
        romaji = romaji.replaceAll("whe", "うぇ");
        romaji = romaji.replaceAll("who", "うぉ");
        romaji = romaji.replaceAll("wyi", "ゐ");
        romaji = romaji.replaceAll("wye", "ゑ");
        romaji = romaji.replaceAll("xa", "ぁ");
        romaji = romaji.replaceAll("xi", "ぃ");
        romaji = romaji.replaceAll("xu", "ぅ");
        romaji = romaji.replaceAll("xe", "ぇ");
        romaji = romaji.replaceAll("xo", "ぉ");
        romaji = romaji.replaceAll("xya", "ゃ");
        romaji = romaji.replaceAll("xyu", "ゅ");
        romaji = romaji.replaceAll("xyo", "ょ");
        romaji = romaji.replaceAll("xtu", "っ");
        romaji = romaji.replaceAll("la", "ぁ");
        romaji = romaji.replaceAll("li", "ぃ");
        romaji = romaji.replaceAll("lu", "ぅ");
        romaji = romaji.replaceAll("le", "ぇ");
        romaji = romaji.replaceAll("lo", "ぉ");
        romaji = romaji.replaceAll("lya", "ゃ");
        romaji = romaji.replaceAll("lyu", "ゅ");
        romaji = romaji.replaceAll("lyo", "ょ");
        romaji = romaji.replaceAll("ltu", "っ");
        romaji = romaji.replaceAll("ka", "か");
        romaji = romaji.replaceAll("ki", "き");
        romaji = romaji.replaceAll("ku", "く");
        romaji = romaji.replaceAll("ke", "け");
        romaji = romaji.replaceAll("ko", "こ");
        romaji = romaji.replaceAll("kya", "きゃ");
        romaji = romaji.replaceAll("kyi", "きぃ");
        romaji = romaji.replaceAll("kyu", "きゅ");
        romaji = romaji.replaceAll("kye", "きぇ");
        romaji = romaji.replaceAll("kyo", "きょ");
        romaji = romaji.replaceAll("ga", "が");
        romaji = romaji.replaceAll("gi", "ぎ");
        romaji = romaji.replaceAll("gu", "ぐ");
        romaji = romaji.replaceAll("ge", "げ");
        romaji = romaji.replaceAll("go", "ご");
        romaji = romaji.replaceAll("gya", "ぎゃ");
        romaji = romaji.replaceAll("gyi", "ぎぃ");
        romaji = romaji.replaceAll("gyu", "ぎゅ");
        romaji = romaji.replaceAll("gye", "ぎぇ");
        romaji = romaji.replaceAll("gyo", "ぎょ");
        romaji = romaji.replaceAll("tsa", "つぁ");
        romaji = romaji.replaceAll("tsi", "つぃ");
        romaji = romaji.replaceAll("tsu", "つ");
        romaji = romaji.replaceAll("tse", "つぇ");
        romaji = romaji.replaceAll("tso", "つぉ");
        romaji = romaji.replaceAll("sa", "さ");
        romaji = romaji.replaceAll("si", "し");
        romaji = romaji.replaceAll("su", "す");
        romaji = romaji.replaceAll("se", "せ");
        romaji = romaji.replaceAll("so", "そ");
        romaji = romaji.replaceAll("ca", "か");
        romaji = romaji.replaceAll("ci", "し");
        romaji = romaji.replaceAll("cu", "く");
        romaji = romaji.replaceAll("ce", "せ");
        romaji = romaji.replaceAll("co", "こ");
        romaji = romaji.replaceAll("sha", "しゃ");
        romaji = romaji.replaceAll("shi", "し");
        romaji = romaji.replaceAll("shu", "しゅ");
        romaji = romaji.replaceAll("she", "しぇ");
        romaji = romaji.replaceAll("sho", "しょ");
        romaji = romaji.replaceAll("sya", "しゃ");
        romaji = romaji.replaceAll("syi", "しぃ");
        romaji = romaji.replaceAll("syu", "しゅ");
        romaji = romaji.replaceAll("sye", "しぇ");
        romaji = romaji.replaceAll("syo", "しょ");
        romaji = romaji.replaceAll("za", "ざ");
        romaji = romaji.replaceAll("zi", "じ");
        romaji = romaji.replaceAll("zu", "ず");
        romaji = romaji.replaceAll("ze", "ぜ");
        romaji = romaji.replaceAll("zo", "ぞ");
        romaji = romaji.replaceAll("ja", "じゃ");
        romaji = romaji.replaceAll("ji", "じ");
        romaji = romaji.replaceAll("ju", "じゅ");
        romaji = romaji.replaceAll("je", "じぇ");
        romaji = romaji.replaceAll("jo", "じょ");
        romaji = romaji.replaceAll("jya", "じゃ");
        romaji = romaji.replaceAll("jyi", "じぃ");
        romaji = romaji.replaceAll("jyu", "じゅ");
        romaji = romaji.replaceAll("jye", "じぇ");
        romaji = romaji.replaceAll("jyo", "じょ");
        romaji = romaji.replaceAll("zya", "じゃ");
        romaji = romaji.replaceAll("zyi", "じぃ");
        romaji = romaji.replaceAll("zyu", "じゅ");
        romaji = romaji.replaceAll("zye", "じぇ");
        romaji = romaji.replaceAll("zyo", "じょ");
        romaji = romaji.replaceAll("ta", "た");
        romaji = romaji.replaceAll("ti", "ち");
        romaji = romaji.replaceAll("tu", "つ");
        romaji = romaji.replaceAll("te", "て");
        romaji = romaji.replaceAll("to", "と");
        romaji = romaji.replaceAll("tya", "ちゃ");
        romaji = romaji.replaceAll("tyi", "ちぃ");
        romaji = romaji.replaceAll("tyu", "ちゅ");
        romaji = romaji.replaceAll("tye", "ちぇ");
        romaji = romaji.replaceAll("tyo", "ちょ");
        romaji = romaji.replaceAll("tha", "てゃ");
        romaji = romaji.replaceAll("thi", "てぃ");
        romaji = romaji.replaceAll("thu", "てゅ");
        romaji = romaji.replaceAll("the", "てぇ");
        romaji = romaji.replaceAll("tho", "てょ");
        romaji = romaji.replaceAll("cya", "ちゃ");
        romaji = romaji.replaceAll("cyi", "ちぃ");
        romaji = romaji.replaceAll("cyu", "ちゅ");
        romaji = romaji.replaceAll("cye", "ちぇ");
        romaji = romaji.replaceAll("cyo", "ちょ");
        romaji = romaji.replaceAll("cha", "ちゃ");
        romaji = romaji.replaceAll("chi", "ち");
        romaji = romaji.replaceAll("chu", "ちゅ");
        romaji = romaji.replaceAll("che", "ちぇ");
        romaji = romaji.replaceAll("cho", "ちょ");
        romaji = romaji.replaceAll("da", "だ");
        romaji = romaji.replaceAll("di", "ぢ");
        romaji = romaji.replaceAll("du", "づ");
        romaji = romaji.replaceAll("de", "で");
        romaji = romaji.replaceAll("do", "ど");
        romaji = romaji.replaceAll("dya", "ぢゃ");
        romaji = romaji.replaceAll("dyi", "ぢぃ");
        romaji = romaji.replaceAll("dyu", "ぢゅ");
        romaji = romaji.replaceAll("dye", "ぢぇ");
        romaji = romaji.replaceAll("dyo", "ぢょ");
        romaji = romaji.replaceAll("dha", "でゃ");
        romaji = romaji.replaceAll("dhi", "でぃ");
        romaji = romaji.replaceAll("dhu", "でゅ");
        romaji = romaji.replaceAll("dhe", "でぇ");
        romaji = romaji.replaceAll("dho", "でょ");
        romaji = romaji.replaceAll("na", "な");
        romaji = romaji.replaceAll("ni", "に");
        romaji = romaji.replaceAll("nu", "ぬ");
        romaji = romaji.replaceAll("ne", "ね");
        romaji = romaji.replaceAll("no", "の");
        romaji = romaji.replaceAll("nya", "にゃ");
        romaji = romaji.replaceAll("nyi", "にぃ");
        romaji = romaji.replaceAll("nyu", "にゅ");
        romaji = romaji.replaceAll("nye", "にぇ");
        romaji = romaji.replaceAll("nyo", "にょ");
        romaji = romaji.replaceAll("pha", "ふぁ");
        romaji = romaji.replaceAll("phi", "ふぃ");
        romaji = romaji.replaceAll("phu", "ふ");
        romaji = romaji.replaceAll("phe", "ふぇ");
        romaji = romaji.replaceAll("pho", "ふぉ");
        romaji = romaji.replaceAll("ha", "は");
        romaji = romaji.replaceAll("hi", "ひ");
        romaji = romaji.replaceAll("hu", "ふ");
        romaji = romaji.replaceAll("he", "へ");
        romaji = romaji.replaceAll("ho", "ほ");
        romaji = romaji.replaceAll("hya", "ひゃ");
        romaji = romaji.replaceAll("hyi", "ひぃ");
        romaji = romaji.replaceAll("hyu", "ひゅ");
        romaji = romaji.replaceAll("hye", "ひぇ");
        romaji = romaji.replaceAll("hyo", "ひょ");
        romaji = romaji.replaceAll("fa", "ふぁ");
        romaji = romaji.replaceAll("fi", "ふぃ");
        romaji = romaji.replaceAll("fu", "ふ");
        romaji = romaji.replaceAll("fe", "ふぇ");
        romaji = romaji.replaceAll("fo", "ふぉ");
        romaji = romaji.replaceAll("fya", "ふゃ");
        romaji = romaji.replaceAll("fyi", "ふぃ");
        romaji = romaji.replaceAll("fyu", "ふゅ");
        romaji = romaji.replaceAll("fye", "ふぇ");
        romaji = romaji.replaceAll("fyo", "ふょ");
        romaji = romaji.replaceAll("pa", "ぱ");
        romaji = romaji.replaceAll("pi", "ぴ");
        romaji = romaji.replaceAll("pu", "ぷ");
        romaji = romaji.replaceAll("pe", "ぺ");
        romaji = romaji.replaceAll("po", "ぽ");
        romaji = romaji.replaceAll("pya", "ぴゃ");
        romaji = romaji.replaceAll("pyi", "ぴぃ");
        romaji = romaji.replaceAll("pyu", "ぴゅ");
        romaji = romaji.replaceAll("pye", "ぴぇ");
        romaji = romaji.replaceAll("pyo", "ぴょ");
        romaji = romaji.replaceAll("ba", "ば");
        romaji = romaji.replaceAll("bi", "び");
        romaji = romaji.replaceAll("bu", "ぶ");
        romaji = romaji.replaceAll("be", "べ");
        romaji = romaji.replaceAll("bo", "ぼ");
        romaji = romaji.replaceAll("bya", "びゃ");
        romaji = romaji.replaceAll("byi", "びぃ");
        romaji = romaji.replaceAll("byu", "びゅ");
        romaji = romaji.replaceAll("bye", "びぇ");
        romaji = romaji.replaceAll("byo", "びょ");
        romaji = romaji.replaceAll("va", "ヴァ");
        romaji = romaji.replaceAll("vi", "ヴィ");
        romaji = romaji.replaceAll("vu", "ヴ");
        romaji = romaji.replaceAll("ve", "ヴェ");
        romaji = romaji.replaceAll("vo", "ヴォ");
        romaji = romaji.replaceAll("vya", "ヴャ");
        romaji = romaji.replaceAll("vyu", "ヴュ");
        romaji = romaji.replaceAll("vyo", "ヴョ");
        romaji = romaji.replaceAll("ma", "ま");
        romaji = romaji.replaceAll("mi", "み");
        romaji = romaji.replaceAll("mu", "む");
        romaji = romaji.replaceAll("me", "め");
        romaji = romaji.replaceAll("mo", "も");
        romaji = romaji.replaceAll("mya", "みゃ");
        romaji = romaji.replaceAll("myi", "みぃ");
        romaji = romaji.replaceAll("myu", "みゅ");
        romaji = romaji.replaceAll("mye", "みぇ");
        romaji = romaji.replaceAll("myo", "みょ");
        romaji = romaji.replaceAll("rya", "りゃ");
        romaji = romaji.replaceAll("ryi", "りぃ");
        romaji = romaji.replaceAll("ryu", "りゅ");
        romaji = romaji.replaceAll("rye", "りぇ");
        romaji = romaji.replaceAll("ryo", "りょ");
        romaji = romaji.replaceAll("ya", "や");
        romaji = romaji.replaceAll("yu", "ゆ");
        romaji = romaji.replaceAll("ye", "いぇ");
        romaji = romaji.replaceAll("yo", "よ");
        romaji = romaji.replaceAll("ra", "ら");
        romaji = romaji.replaceAll("ri", "り");
        romaji = romaji.replaceAll("ru", "る");
        romaji = romaji.replaceAll("re", "れ");
        romaji = romaji.replaceAll("ro", "ろ");
        romaji = romaji.replaceAll("wa", "わ");
        romaji = romaji.replaceAll("wi", "うぃ");
        romaji = romaji.replaceAll("wu", "う");
        romaji = romaji.replaceAll("we", "うぇ");
        romaji = romaji.replaceAll("wo", "を");
        romaji = romaji.replaceAll("a", "あ");
        romaji = romaji.replaceAll("i", "い");
        romaji = romaji.replaceAll("u", "う");
        romaji = romaji.replaceAll("e", "え");
        romaji = romaji.replaceAll("o", "お");
        romaji = romaji.replaceAll("n", "ん");
        romaji = romaji.replaceAll("!", "！");
        romaji = romaji.replaceAll("\\?", "？");
        romaji = romaji.replaceAll("~", "～");
        romaji = romaji.replaceAll("-", "ー");
        romaji = romaji.replaceAll("\\[", "「");
        romaji = romaji.replaceAll("]", "」");
        romaji = romaji.replaceAll(",", "、");
        return romaji;
    }

    @Nullable
    private static String conv(String text) {
        if (!text.isEmpty()) {
            HttpURLConnection urlconn = null;
            BufferedReader reader = null;

            try {
                String baseurl = "https://www.google.com/transliterate?langpair=ja-Hira%7Cja&text=" + URLEncoder.encode(text, StandardCharsets.UTF_8);

                URL url = URI.create(baseurl).toURL();
                urlconn = (HttpURLConnection) url.openConnection();
                urlconn.setRequestMethod("GET");
                urlconn.setInstanceFollowRedirects(false);
                urlconn.connect();
                reader = new BufferedReader(new InputStreamReader(urlconn.getInputStream(), StandardCharsets.UTF_8));
                String line;
                StringBuilder result = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    result.append(parseIMEResponse(line));
                }

                return result.toString();
            } catch (IOException var24) {
                var24.printStackTrace();
            } finally {
                if (urlconn != null) {
                    urlconn.disconnect();
                }

                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException ignored) {
                    }
                }

            }

        }
        return null;
    }

    private static String parseIMEResponse(String result) {
        StringBuilder buf = new StringBuilder();
        int level = 0;
        int index = 0;

        while(index < result.length()) {
            int nextStart;
            int nextEnd;
            if (level >= 3) {
                nextStart = result.indexOf("\"", index);
                nextEnd = result.indexOf("\"", nextStart + 1);
                if (nextStart == -1 || nextEnd == -1) {
                    return buf.toString();
                }

                buf.append(result, nextStart + 1, nextEnd);
                int next = result.indexOf("]", nextEnd);
                if (next == -1) {
                    return buf.toString();
                }

                --level;
                index = next + 1;
            } else {
                nextStart = result.indexOf("[", index);
                nextEnd = result.indexOf("]", index);
                if (nextStart == -1) {
                    return buf.toString();
                }

                if (nextStart < nextEnd) {
                    ++level;
                    index = nextStart + 1;
                } else {
                    --level;
                    index = nextEnd + 1;
                }
            }
        }

        return buf.toString();
    }
}