package com.hzxy.utils;

import org.apache.commons.lang3.StringUtils;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 文章处理工具类
 */
public class MyUtils {
     /**
     * 提取html中的文字
      * String html = "<p>这是一段 <strong>HTML</strong> 内容</p>";
      * String text = MyUtils.htmlToText(html);
      * // 输出: "这是一段  HTML  内容"（标签被替换为空格）
     */
    public static String htmlToText(String html) {
        if (StringUtils.isNotBlank(html)) {
            return html.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", " ");
        }
        return "";
    }

    /**
     * Markdown转换为Html
     * String md = "## 标题\n 这是一段 **Markdown** 内容，包含表格：\n| 列1 | 列2 |";
     * String html = MyUtils.mdToHtml(md);
     * //输出: "<h2>标题</h2>\n<p>这是一段 <strong>Markdown</strong> 内容，包含表格：</p>\n<table><thead><tr><th>
     *     列1</th><th>列2</th></tr></thead><tbody></tbody></table>"
     */
    public static String mdToHtml(String markdown) {
        if (StringUtils.isBlank(markdown)) {//判断 markdown 是否 为空或空白字符串
            return "";//如果是空白内容，直接返回空字符串
        }
        //获取 表格扩展，让 commonmark 支持解析 Markdown 表格语法（比如 | 表头 | 内容 | 这类格式 ）。
        List<Extension> extensions = Arrays.asList(TablesExtension.create());
        //构建最终的 Parser 对象，后续用它解析 Markdown 文本。
        Parser parser = Parser.builder().extensions(extensions).build();
        //把 Markdown 文本拆成语法结构（比如标题、段落、表格等节点），方便后续渲染成 HTML。
        Node document = parser.parse(markdown);
        //创建 commonmark 的 HtmlRenderer 构建器，用于配置 HTML 渲染规则。
        HtmlRenderer renderer = HtmlRenderer.builder()
                .attributeProviderFactory(context -> new LinkAttributeProvider())
                .extensions(extensions).build();
        //此时 content 就是 Markdown 转成的 HTML，但还没处理表情符号。
        String content = renderer.render(document);
        //调用工具类（比如 Commons）的 emoji 方法，处理 HTML 中的表情符号
        content = Commons.emoji(content);
        return content;
    }

    /**
     * 替换HTML脚本
     * String unsafe = "<script>alert('XSS')</script>";
     * String safe = MyUtils.cleanXSS(unsafe);
     *  输出: "&lt;script&gt;alert(&#39;XSS&#39;)&lt;/script&gt;"（脚本被转义）
     */
    public static String cleanXSS(String value) {
        value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
        value = value.replaceAll("'", "&#39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("script", "");
        return value;
    }

    /**
     * 实现 AttributeProvider 接口，为 Markdown 中的链接（<a> 标签）自动添加 target="_blank" 属性，让链接在新窗口打开。
     */
    private static class LinkAttributeProvider implements AttributeProvider {
        @Override
        public void setAttributes(Node node, String tagName, Map<String, String> attributes) {
            if (node instanceof Link) {
                attributes.put("target", "_blank");
            }
        }
    }
}
