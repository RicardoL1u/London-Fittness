/**
 * This is the controller of video.
 * 
 * @author Norris Zhou
 * @version 1.0
 */

package Controller;

import Database.Video;

public class VideoControl {
    /**
     * This is the constructor.
     */
    public VideoControl() {
    }

    /**
     * Method to get the video path according the videoID.
     * 
     * @param argv all String arguments including videoID
     * @return
     */
    public String playVideo(String... argv) {
        /**
         * If the number of arguments is not enough, the method will return an error
         * code with 1.
         */
        if (argv.length < 1) {
            return "error_code:1\nmsg:Missing parameter.";
        }

        /**
         * argv is expective as (String videoID).
         */
        String videoID = argv[0];

        Video video;
        String response = null;

        /**
         * Pass these data to database and wait for a response from database.
         */
        video = new Video(videoID);
        if (video.getPath().equals("-1")) {
            /**
             * path = -1 means this video cannot be found and this method will return an
             * error code with 2.
             */
            response = "error_code:2\nmsg:Can't find this resource.";
        } else {
            /**
             * If the path is a normal String, the video has been successfully got. This
             * method will return an error code with 0.
             */
            response = "error_code:0\npath:" + video.getPath();
        }

        return response;
    }
}
