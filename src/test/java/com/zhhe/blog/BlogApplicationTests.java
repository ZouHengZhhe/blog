package com.zhhe.blog;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan("com.zhhe.blog.dao")
class BlogApplicationTests
{

    @Test
    void contextLoads()
    {
        System.out.println(judgePwdKeyboardSort("aAedd!"));
    }

    private boolean judgePwdKeyboardSort(String pwd)
    {

        char[][] c1= {
                {'!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '+'},
                {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', '{', '}', '|'},
                {'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', ':', '"'},
                {'z', 'x', 'c', 'v', 'b', 'n', 'm', '<', '>', '?'}
        };
        char[][] c2= {
                {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '-', '='},
                {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', '[', ']', '\\'},
                {'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', ';', '\''},
                {'z', 'x', 'c', 'v', 'b', 'n', 'm', ',', '.', '/'}
        };
        char[] str=pwd.toCharArray();

        int[] y=new int[str.length];
        int[] x=new int[str.length];

        for (int c=0;c<str.length;c++)
        {
            y[c] = 0;//当做~`键处理
            x[c] = -1;
            for (int i = 0; i < c1.length; i++)
            {
                for (int j = 0; j < c1[i].length; j++)
                {
                    if (str[i] == c1[i][j])
                    {
                        y[c] = i;
                        x[c] = j;
                    }
                }
            }

            if (x[c] != -1) continue;
            for (int i = 0; i < c2.length; i++) {
                for (int j = 0; j < c2[i].length; j++) {
                    if (str[c] == c2[i][j]) {
                        y[c] = i;
                        x[c] = j;
                    }
                }
            }
        }
        //匹配坐标连线
        for (int c = 1; c < str.length - 1; c++) {
            if (y[c - 1] == y[c] && y[c] == y[c + 1]) {
                if ((x[c - 1] + 1 == x[c] && x[c] + 1 == x[c + 1]) || (x[c + 1] + 1 == x[c] && x[c] + 1 == x[c - 1])) {
                    return true;
                }
            } else if (x[c - 1] == x[c] && x[c] == x[c + 1]) {
                if ((y[c - 1] + 1 == y[c] && y[c] + 1 == y[c + 1]) || (y[c + 1] + 1 == y[c] && y[c] + 1 == y[c - 1])) {
                    return true;
                }
            }
        }
        return false;
    }


}
