package com.hzxy.utils;


import com.hzxy.model.domain.Article;
import com.vdurmont.emoji.EmojiParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 页面数据展示封装类
 */
 @Component
public class Commons {
    /**
     * 网站链接（获取默认网站链接）
     * 返回默认网站链接（固定追加 /page/1 路径），通常用于首页或分页的基础路径。
     * 若 site_option("site_url") 返回 https://www.example.com，则：
     *    String url = Commons.site_url();
     *    输出: https://www.example.com/page/1
     */
    public static String site_url() {
        return site_url("/page/1");
    }
    /**
     * 返回网站链接下的全址（拼接网站全路径）
     * site_url("/article/12") ->   site_option("site_url")+"/article/12"
     */
    public static String site_url(String sub) {
        return site_option("site_url") + sub;
    }

    /**
     * 网站配置项（获取网站配置项，无默认值）
     * site_option("site_url") -->  site_option("site_url","")
     */
    public static String site_option(String key) {
        return site_option(key, "");
    }

    /**
     * 网站配置项（带默认值的配置项）
     * site_option("site_url","") ->  return ""
     */
    public static String site_option(String key, String defalutValue) {
        //根据 key 获取配置项，若 key 为空则返回 ""，否则返回默认值 defalutValue
        if (StringUtils.isBlank(key)) {
            return "";
        }
        return defalutValue;
    }

    /**
     * 截取字符串
     */
    public static String substr(String str, int len) {
        if (str.length() > len) {
            return str.substring(0, len);
        }
        return str;
    }

    /**
     * 返回日期
     */
     public static String dateFormat(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
     }

    /**
     * 返回文章链接地址（生成文章链接）
     * 根据文章 ID（aid）生成文章详情页的完整 URL（依赖 site_url 拼接）。
     * 假设 aid=12  -->  site_url("/article/12")
     */
    public static String permalink(Integer aid) {
        return site_url("/article/" + aid.toString());
    }

    /**
     * 截取文章摘要
     * Article article = new Article();
     * article.setContent("## 标题\n 内容...<!--more--> 更多内容");
     * String intro = Commons.intro(article, 10);
     * // 输出: "标题 内容......"（Markdown转HTML后提取文本，再截取）
     */
    public static String intro(Article article, int len) {
        String value = article.getContent();
        int pos = value.indexOf("<!--more-->");
        //若内容包含 <!--more--> 标记，截取标记前的内容，转换为纯文本（先 Markdown→HTML→纯文本）。
        //否则转换全文为纯文本，超长则截取前 len 字符并追加 ......。
        if (pos != -1) {
            String html = value.substring(0, pos);
            return MyUtils.htmlToText(MyUtils.mdToHtml(html));
        } else {
            String text = MyUtils.htmlToText(MyUtils.mdToHtml(value));
            if (text.length() > len) {
                return text.substring(0, len)+"......";
            }
            return text;
        }
    }

    /**
     * 对文章内容进行格式转换，将Markdown为Html
     */
    public static String article(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.replace("<!--more-->", "\r\n");
            return MyUtils.mdToHtml(value);
        }
        return "";
    }

    /**
     * 显示文章缩略图，顺序为：文章第一张图 -> 随机获取
     */
    public static String show_thumb(Article article) {
        //判断 article的thumbnail属性是否非空且不是空白字符串
        if (StringUtils.isNotBlank(article.getThumbnail())){
            return article.getThumbnail();//如果有自定义缩略图，直接返回该缩略图路径
        }
        int cid = article.getId();
        int size = cid % 24; //结果范围是 0 ~ 23
        //如果 size == 0（比如文章 ID 是 24、48 等，24 % 24 = 0 ），就把 size 改成 1；否则保持 size 原来的值。
        //让最终 size 范围变成 1 ~ 24
        size = size == 0 ? 1 : size;
        return "/user/img/rand/" + size + ".png";
    }

    /**
     * 这种格式的字符转换为emoji表情
     * String text = Commons.emoji(":smile:");
     * // 输出: "😄"
     */
    public static String emoji(String value) {
        return EmojiParser.parseToUnicode(value);
    }

}
