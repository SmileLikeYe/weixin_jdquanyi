/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smile.weixin.jdquanyi.message.resp;

/**
 * ������Ϣ(��������Ӧ)
 *
 * @author smile
 */
public class MusicMessage extends BaseMessage {

    //����
    private Music Music;

    public void setMusic(Music music) {
        this.Music = music;
    }

    public Music getMusic() {
        return Music;
    }

}
