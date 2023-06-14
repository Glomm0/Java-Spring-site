/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameData.model;

/**
 *
 * @author а353
 */
public class News {

    public void setGid(String gid) {
        this.gid = gid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getGid() {
        return gid;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getContents() {
        return contents;
    }
    public String gid;
    public String title;
    public String url;
    public String contents;
    public String imgUrl;
    public String getImgUrl(){
        String temp="";
        if(contents.indexOf("[img]")!=-1){
        temp="https://cdn.cloudflare.steamstatic.com/steamcommunity/public/images/clans"+contents.substring(contents.indexOf("[img]")+23,contents.indexOf("[/img]"));
        }
        System.out.println(temp);
        return temp;
    }
    public String getShortDescr(){
         String temp="";
         if(contents.indexOf("[img]")!=-1){
         temp=contents.substring(contents.indexOf("[/img]")+6,contents.indexOf("[/img]")+76)+"...";
         }
         
         return temp;
    }
}
