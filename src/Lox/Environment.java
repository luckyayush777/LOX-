package Lox;

import java.util.HashMap;
import java.util.Map;

class Environment {
    final Environment enclosing;
    private final Map<String, Object> values = new HashMap<>();

    Environment()
    {
        enclosing = null;
    }

    Environment(Environment enclosing)
    {
        this.enclosing = enclosing;
    }




    Object get(Token name)
    {
        if(values.containsKey(name.lexeme))
        {
            return values.get(name.lexeme);
        }

        if(enclosing != null)
            return enclosing.get(name);
        throw new RuntimeError(name, "Undefined Variable '" + name.lexeme
        + "'.");
    }


    void define(String name, Object value)
    {
        values.put(name, value);
        //maybe add an error check if the variable already exists
    }

    void assign(Token name, Object value)
    {
        if(values.containsKey(name.lexeme)) {
            //System.out.println("Debug: Environment assigning "
                    //+ name.lexeme + " = " + value);
            values.put(name.lexeme, value);
            return;
        }

        if(enclosing != null)
        {
            enclosing.assign(name, value);
            return;
        }

        throw new RuntimeError(name, "Undefined Variable ' +" +
                name.lexeme + "'.");
    }

}
