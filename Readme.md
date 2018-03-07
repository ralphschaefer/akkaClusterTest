Cluster Test Application
========================

Akka cluster mechanism ist used for building an very basic 
chat application via broadcast router.

Usage
-----

- Start up the seed node (with the "seed.sh" shellscript)
- Start up several client nodes (with the "client.sh" shellscript)

The Cluster nodes should be run in extra terminal, so you can watch the stdout.

Application
-----------

The seed node terminal is only for watching the conversation. If any key ist hit here 
the seed node will terminate.

In the client node terminals you can watch the conversation ans also enter text. The 
text is send to all other terminals.

If you type "exit" the client node terminates.
