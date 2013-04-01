package gd.eval.bot
import org.jibble.pircbot._

import gd.eval.Router

object EvalGDBot extends App {
  new EvalGDBot
}

class EvalGDBot extends PircBot {
  setName("eval-gd")
  setLogin("eval-gd")
  setVerbose(true)
  connect("quartz.tenthbit.net", 6667)
  joinChannel("#programming")

  override def onMessage(
    channel: String,
    sender: String,
    login: String,
    hostname: String,
    message: String) {

    if (message.startsWith("@@")) {
      message.split(" ", 2) match {
        case Array(command) => command.drop(2) match {
          case "help" => sendMessage(channel, "Hi. I'm eval-gd, a product of eval.gd.")
          case _ =>
        }
        case Array(command, arguments) => {
          Router.route(command.drop(2), arguments) match {
            case Some(sandbox) => sandbox.evaluate.fold(
              exception => sendMessage(channel, "An internal security error has occurred."),
              result => sendMessage(channel, result.toString)
            )
            case None => sendMessage(channel, "Invalid language.")
          }
        }
      }
    }
  }

  /** Reconnect when we get disconnected from the network. */
  override def onDisconnect() = new EvalGDBot
}
