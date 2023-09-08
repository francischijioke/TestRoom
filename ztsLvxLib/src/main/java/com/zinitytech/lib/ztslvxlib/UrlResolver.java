package src.main.java.com.zinitytech.lib.ztslvxlib;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlResolver {

    String resolvedUrl = "";
    String protocol = "";
    String scheme;
    String path;
    String url;
    String domain;
    String http = "^http://";
    String https = "^https://";



    public UrlResolver(String url) {

        this.url = removeLastSlashIfExist(url);
        resolveMainUrl();
        path = getResolvedExtLinkPath(getExternal(resolvedUrl));
        domain = resolveDomain(url);

    }


    public String resolveLinking(String link) {
        String http = "^http://";
        String https = "^https://";
        String back2 = "^(\\.\\./)+";
        String back3 = "^(\\./)";


        if (Pattern.compile(http).matcher(link).find()) {
            return link;
        }

        if (Pattern.compile(https).matcher(link).find()) {
            return link;
        }


        if (Pattern.compile(back3).matcher(link).find()) {
            link = link.substring(2);
        }


        if (isLinkWithSlashAndId(link) || link.equals("/")) {
            //let islash = link.substring(2);
            return getMainPageUrl();
        }

        if (containsInitialSlash(link)) {
            // String islash = link.substring(1);
            return getMainPageUrl() + link;
        }

        if (Pattern.compile(back2).matcher(link).find()) {
            int len = getBackLinkCount(link);
            int n = len * 3;
            String ru = link.substring(n);
            String finishPath = resolvedUrl;

            for (int i = 0; i < len + 1; i++) {

                int last = finishPath.lastIndexOf("/");
                if (last != -1) {
                    finishPath = finishPath.substring(0, last);
                }

            }

            return protocol + finishPath + "/" + ru;
        }

        int r = resolvedUrl.lastIndexOf('/');
        String ru = resolvedUrl.substring(0, r);

        if (r != -1) {
            return protocol + ru + "/" + link;
        } else {
            return protocol + resolvedUrl + "/" + link;
        }


        //return link;
    }


    private int getBackLinkCount(String path) {
        String back = "(\\.\\./)";
        Pattern pattern = Pattern.compile(back);
        Matcher matcher = pattern.matcher(path);
        int len = 0;
        while (matcher.find()) {
            len++;
        }

        return len;
    }

    public String resolveLinkFromFullUrl2(String link) {
        String back = "(\\.\\./)";
        String back2 = "^(\\.\\./)+";

        if (urlContainProtocol(link)) {
            link = removeLastSlashIfExist(removeProtocol(link));
            int index = link.indexOf("/");

            if (index != -1) {
                return path+link.substring(index + 1);
            }

        } else {
            if (containsInitialSlash(link) && !isLinkWithSlashAndId(link)) {
                String l = link.substring(1);
                return path+l;
            }

            if (Pattern.compile(back2).matcher(link).find()) {

                int len = Pattern.compile(back).matcher(link).groupCount();
                int n = len * 3;
                if (len > getExternal(resolvedUrl)) {
                    return path+link.substring(n);
                }

                return link;
            }
            //url starts with ./





        }

        return link;
    }

    public String removeProtocol(String link) {

        String url = link;

        if (Pattern.compile(http).matcher(url).find() ) url = link.substring(7);

        if (Pattern.compile(https).matcher(url).find()) url = link.substring(8);
        //console.log("my url :" +url);
        return  url;
    }


    private void resolveMainUrl() {
        String http = "^http://";
        String https = "^https://";

        if (Pattern.compile(http).matcher(url).find()) {
            resolvedUrl = url.substring(7);
            protocol = "http://";
            scheme = "http:";
        }

        if (Pattern.compile(https).matcher(url).find()) {
            resolvedUrl = url.substring(8);
            protocol = "https://";
            scheme = "https:";
        }

        String r = resolvedUrl.substring(resolvedUrl.length() - 1);
        if (r.equals("/")) {
            resolvedUrl = resolvedUrl.substring(0, resolvedUrl.length() - 1);
        }

        //console.log("resolved: "+resolvedUrl);


    }


    private int getExternal(String link) {

        int last = link.lastIndexOf("/");
        if (last != -1) {
            int count = 0;
            String p = link.substring(0, last);
            while (p.lastIndexOf("/") != -1) {
                count = count + 1;
                p = p.substring(0, p.lastIndexOf("/"));
            }


            return count;
        }

        return 0;
    }

    private String getResolvedExtLinkPath(int count) {

        StringBuilder p = new StringBuilder();
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                p.append("../");
            }

        }
        return p.toString();
    }


    private String getMainPageUrl() {
        return protocol + domain;
    }

    private boolean isLinkWithSlashAndId(String link) {
        String patt = "^/#";
        return (Pattern.compile(patt).matcher(link).find());
    }


    private String resolveDomain(String link) {

        String u = link;

        if (Pattern.compile(http).matcher(link).find()) {
            String url = link.substring(7);
            u = removeLastSlashIfExist(url);
        }

        if (Pattern.compile(https).matcher(link).find()) {
            String url = link.substring(8);
            u = removeLastSlashIfExist(url);
        }

        int index = u.indexOf("/");
        if (index != -1) {
            u = u.substring(0, index);
        }

        return addWWWIfNotAvailable(u);
    }

    private String addWWWIfNotAvailable(String link) {
        String rex = "^(www\\.)";
        if (link.length() > 4) {
            if (!Pattern.compile(rex).matcher(link).find()) {
                return "www." + link;
            }
        }

        return link;

    }



    private String removeLastSlashIfExist(String link) {
        if (link != null) {
            String rex = "/$";
            if (Pattern.compile(rex).matcher(link).find()) {
                if (link.length() > 1) {
                    return link.substring(0, link.length() - 1);
                }
            }
        }

        return link;
    }




    private boolean containsInitialSlash(String link) {
        String rex = "^/";
        return (Pattern.compile(rex).matcher(link).find());
    }


    public String anyCharAfterImageNameExt(String link) {
        int index = link.indexOf("?");
        if (index != -1) {
            return link.substring(0,index);
        }

        return link;
    }


    public String getExternalLinkingPath(String link) {

        if (Pattern.compile(http).matcher(link).find()) {
            return path+"external/"+addWWWIfNotAvailable(link.substring(7));
        }

        if (Pattern.compile(https).matcher(link).find()) {
            return path+"external/"+addWWWIfNotAvailable(link.substring(8));
        }

        return link;
    }

    public boolean isUrlFullPath(String link) {
        if (Pattern.compile(http).matcher(link).find()) {
            return true;
        }

        return Pattern.compile(https).matcher(link).find();
    }


    public boolean isUrlFromExternalDomain(String link) {
        String http = "^http://";
        String https = "^https://";

        if (Pattern.compile(http).matcher(link).find()) {
            String url = link.substring(7);
            int index = url.indexOf("/");
            if (index != -1) {
                url = url.substring(0,index);
                if (addWWWIfNotAvailable(url).equals(addWWWIfNotAvailable(domain))) {
                    return false;
                }

            }
        }

        if (Pattern.compile(https).matcher(link).find()) {
            String url = link.substring(8);
            int index = url.indexOf("/");
            if (index != -1) {
                url = url.substring(0,index);
                //System.out.println("url domain : "+addWWWIfNotAvailable(url)+"    "+domain);
                if (addWWWIfNotAvailable(url).equals(addWWWIfNotAvailable(domain))) {
                    return false;
                }

            }
        }

        return Pattern.compile(https).matcher(link).find() || Pattern.compile(http).matcher(link).find();
    }

    private boolean urlContainProtocol(String link) {
        String http = "^http://";
        String https = "^https://";

        return (Pattern.compile(https).matcher(link).find() || Pattern.compile(http).matcher(link).find());
    }



}
