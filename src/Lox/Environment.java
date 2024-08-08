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

            //a check for accessing unassigned values
            if(values.get(name.lexeme) != null)
                return values.get(name.lexeme);
            else
                throw new RuntimeError(name, "Trying to access unassigned " +
                        "value");
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
        if(values.containsKey(name.lexeme))
        {
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
