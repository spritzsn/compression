package io.github.spritzsn.compression

import io.github.spritzsn.spritz.{Request, Response, Server}

@main def run(): Unit =
  Server("TestServer/1") { app =>
    app
      .use(apply())
      .get("/", (req: Request, res: Response) => res.send("this should have been compressed correctly"))
    app.listen(3000)
    println("listening")
  }
