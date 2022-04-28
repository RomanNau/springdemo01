package org.romannau.springdemo01.controllers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class DemoController {

    String dollarCourse=null;


    @GetMapping("/")

    public String parsing(Model model) throws IOException {
        Document doc = Jsoup.connect("https://cbr.ru/").get();   /*получаем html страницу для дальнешего разбора ее в Sping*/

        Elements rateUSD = doc.getElementsByClass("main-indicator_rate");  /*ищем по тэгу "main-indicator_rate"*/

        for(int i = 0; i < rateUSD.size(); i++) {
            Element rateUSD01 = rateUSD.get(i);   /* записываем результат поиска по тэгу "main-indicator_rate"  в список */

            Elements rateUSD02 = rateUSD01.getElementsByClass("_dollar");   /*по вышеполученному списку ищем по тэгу "_dollar"*/
            if (!rateUSD02.isEmpty()) {
                System.out.println(rateUSD01.text());    /*проверка нового списка на наполненость*/

                Elements rateUSD03 = rateUSD01.getElementsByClass("mono-num");   /* поиск по тэгу "mono-num"*/
                Element rateUSD04 = rateUSD03.get(0);  /*получение первого элемента(курс на сегодня) в списке*/

                dollarCourse = rateUSD04.text();  /*перевод в текстовый формат переменной dollarCourse*/
                break;
            } else {
                dollarCourse = "пустота";
            }
            System.out.println(dollarCourse);  /*вывод на консоль dollarCourse-необязательная проверка*/
        return dollarCourse;
        }
        model.addAttribute("dollarCourse", dollarCourse);
        return "home";

    }
}
