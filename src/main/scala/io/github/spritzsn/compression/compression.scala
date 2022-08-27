package io.github.spritzsn.compression

import io.github.spritzsn.spritz.{DMap, HandlerResult, RequestHandler}

import java.io.ByteArrayOutputStream
import java.util.zip.GZIPOutputStream

def apply(): RequestHandler =
  (req, res) =>
    req.headers get "accept-encoding" match
      case Some(encodings) if encodings contains "gzip" =>
        res action {
          if res.body.length > 23 then
            // https://stackoverflow.com/a/46739747/13420474 // Mark Adler wrote the decompression // https://zlib.net/
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
