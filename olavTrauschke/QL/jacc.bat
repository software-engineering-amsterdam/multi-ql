del "%~dp0\src\org\uva\sea\ql\parser\Parser.java" /q
del "%~dp0\src\org\uva\sea\ql\parser\Tokens.java" /q
java -jar "%~dp0\lib\jacc.jar" "%~dp0\src\org\uva\sea\ql\parser\grammar.jacc"
