package stack;

import java.util.Stack;

/**
 * @author xz
 * @date 2020/2/18 12:55
 **/

//https://leetcode-cn.com/problems/valid-parentheses/  有效的括号
public class L_20_ValidBracket {
    public boolean isValid1(String s) {
        while(s.contains("()")
                ||s.contains("{}")
                ||s.contains("[]")){
            s = s.replace("{}", "");
            s = s.replace("()", "");
            s = s.replace("[]", "");
        }
        if(s.length()==0) return true;
        return false;
    }

    public boolean isValid2(String s) {
        Stack<Character> stack=new Stack<Character>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            Character character=s.charAt(i);
            //如果遇到左括号则入队
            if (character == '(' || character == '{' || character == '['){
                stack.push(character);
            }else{
                //如果遍历到右括号时栈为空,则直接返回false
                if(stack.isEmpty())  return false;
                Character left=stack.pop();
                //如果遇到的是右括号则出栈进行匹配
                if (left == '(' && character != ')') return false;
                if (left == '{' && character != '}') return false;
                if (left == '[' && character!= ']') return false;
            }
        }
        return stack.isEmpty();
    }
}
