package Lox;

import java.util.HashMap;
import java.util.Map;

class Environment {
    private final Map<String, Object> values = new HashMap<>();

    Object get(Token name)
    {
        if(values.containsKey(name.lexeme))
        {
            return values.get(name.lexeme);
        }
        throw new RuntimeError(name, "Undefined Variable '" + name.lexeme
        + "'.");
    }


    void define(String name, Object value)
    {
        values.put(name, value);
        //maybe add an error check if the variable already exists
    }
}
