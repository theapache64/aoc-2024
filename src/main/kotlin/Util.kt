
fun getResource(filename: String) = Thread.currentThread().contextClassLoader
    .getResourceAsStream(filename)
    ?.bufferedReader()