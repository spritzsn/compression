package io.github.spritzsn.compression

import io.github.spritzsn.spritz.{DMap, HandlerResult, RequestHandler2, Request, Response}

import java.io.ByteArrayOutputStream
import java.util.zip.GZIPOutputStream

def apply(): RequestHandler2 =
  (req: Request, res: Response) =>
    req.headers get "accept-encoding" match
      case Some(encodings) if encodings contains "gzip" =>
        res action {
          val gzipped = gzipCompress(res.body)

          if gzipped.length < res.body.length then
            res.body = gzipped
            res.set("Content-Encoding", "gzip")
            res.set("Content-Length", res.body.length)
        }
      case _ =>

    HandlerResult.Next

def gzipCompress(input: Array[Byte]): Array[Byte] =
  val output = new ByteArrayOutputStream
  val out = new GZIPOutputStream(output)

  out.write(input)
  out.close()
  output.toByteArray
