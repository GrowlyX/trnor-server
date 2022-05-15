# trnor-server
Making good use of [Discord](https://discord.com)'s chat replacement functionality.

## What is it?
This is used to wildcard-target endpoints of `trnor.com` (a domain I own), and return a modified version of the [tenor](https://tenor.com) GIF based on the  id provided at the end of the endpoint.

## How does it work?
When a message with the following syntax:
- `s/<x>/<y>`
  - More information on this functionality can be seen [here](https://www.geeksforgeeks.org/sed-command-in-linux-unix-with-examples/).

is sent in any text-based Discord channel, your last sent message is edited, replacing the first occurrence of `x`, with `y`.

By typing `s/e/r`, you're able to change any `tenor.com` URL, to my `trnor.com` URL.

## Additional Information:
 - trnor-server is built with [Kotlin](https://kotlinlang.org) using the [Javalin](https://javalin.io/) platform.
 - We use a basic mapping of [Tenor's GifAPI](https://tenor.com/gifapi/documentation#quickstart).
 - By default, we return a templated meme with the provided gif overlayed on it.
   - The template can be seen here: [ok-i-pull-up.png](https://github.com/GrowlyX/trnor-server/blob/master/ok-i-pull-up.png)
