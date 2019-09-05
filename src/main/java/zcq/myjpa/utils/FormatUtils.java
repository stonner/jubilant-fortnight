package zcq.myjpa.utils;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/09/05
 */
public class FormatUtils {

    /**

     * get json data backspace use '\t' new line use '\n'

     */

    public static String formatToJson(String region) {

        int level = 0;

        StringBuffer preBuffer = new StringBuffer();

        for (int i = 0; i < region.length(); i++) {

            char c = region.charAt(i);

            if (level > 0 && '\n' == preBuffer.charAt(preBuffer.length() - 1)) {

                preBuffer.append(getLevelStr(level));

            }

            switch (c) {

                case '{':

                case '[':

                    preBuffer.append(c + "\n");

                    level++;

                    break;

                case ',':

                    preBuffer.append(c + "\n");

                    break;

                case '}':

                case ']':

                    preBuffer.append("\n");

                    level--;

                    preBuffer.append(getLevelStr(level));

                    preBuffer.append(c);

                    break;

                default:

                    preBuffer.append(c);

                    break;

            }

        }



        return String.valueOf(preBuffer);



    }

    private static String getLevelStr(int level) {

        StringBuffer lb = new StringBuffer();

        for (int levelTmp = 0; levelTmp < level; levelTmp++) {

            lb.append("\t");

        }

        return String.valueOf(lb);

    }
}
