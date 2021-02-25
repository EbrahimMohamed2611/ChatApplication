<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
    <xsl:output method="html"/>
    <xsl:template match="messages">
        <html>
            <head>
                <style type="text/css">
                    @import url(http://fonts.googleapis.com/css?family=Lato:100,300,400,700);
                    @import url(http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css);
                    html,
                    body {
                    background: linear-gradient(90deg, rgba(2,0,36,1) 0%, rgba(0,212,255,1) 0%, rgba(64,64,222,1) 100%);

                    font-family: 'Lato', sans-serif;
                    margin: 0px auto;
                    }

                    ::selection {
                    background: rgba(82, 179, 217, 0.3);
                    color: inherit;
                    }

                    a {
                    color: rgba(82, 179, 217, 0.9);
                    }

                    .menu {

                    top: 0px;
                    left: 0px;
                    right: 0px;
                    width: 100%;
                    height: 50px;
                    background: rgba(82, 179, 217, 0.9);
                    z-index: 100;
                    -webkit-touch-callout: none;
                    -webkit-user-select: none;
                    -moz-user-select: none;
                    -ms-user-select: none;
                    }

                    .logo {
                    text-align: center;
                    font-size: 25px;
                    color: white;
                    }

                    .name {
                    position: absolute;
                    top: 3px;
                    left: 110px;
                    font-family: 'Lato';
                    font-size: 25px;
                    font-weight: 300;
                    color: rgba(255, 255, 255, 0.98);
                    cursor: default;
                    }

                    .chat {
                    list-style: none;
                    background: none;
                    margin: 0;
                    padding: 0 0 50px 0;

                    }

                    .chat li {
                    padding: 0.5rem;
                    overflow: hidden;
                    display: flex;
                    }

                    .chat .avatar img {
                    width: 40px;
                    height: 40px;
                    border-radius: 100%;
                    -webkit-border-radius: 100%;
                    -moz-border-radius: 100%;
                    -ms-border-radius: 100%;
                    background-color: rgba(255, 255, 255, 0.9);
                    -webkit-touch-callout: none;
                    -webkit-user-select: none;
                    -moz-user-select: none;
                    -ms-user-select: none;
                    }

                    .other .msg {
                    order: 1;
                    border-radius: 15px;
                    border-top-left-radius: 0px;
                    box-shadow: -1px 2px 0px #D4D4D4;
                    background: palevioletred;
                    }



                    .self {
                    justify-content: flex-end;
                    align-items: flex-end;
                    }

                    .self .msg {
                    order: 1;
                    border-radius: 15px;
                    border-bottom-right-radius: 0px;
                    box-shadow: 1px 2px 0px #D4D4D4;
                    background: cadetblue;
                    }

                    .msg {
                    background: white;
                    min-width: 50px;
                    padding: 10px;
                    border-radius: 2px;
                    box-shadow: 0px 2px 0px rgba(0, 0, 0, 0.07);
                    }

                    .msg p {
                    font-size: 2em;
                    margin: 0 0 0.2rem 0;
                    color: white;
                    }

                    .msg img {
                    position: relative;
                    display: block;
                    width: 450px;
                    border-radius: 5px;
                    box-shadow: 0px 0px 3px #eee;
                    transition: all .4s cubic-bezier(0.565, -0.260, 0.255, 1.410);
                    cursor: default;
                    -webkit-touch-callout: none;
                    -webkit-user-select: none;
                    -moz-user-select: none;
                    -ms-user-select: none;
                    }
                </style>
                <head>
                    <meta charset="UTF-8"/>
                    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
                    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
                    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.min.css"/>
                    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0-11/css/all.min.css"/>

                </head>
            </head>
            <body>
                <div class="container" style="width:50%">
        <h1 class="text-center">Chat Session</h1>
                <ol class="chat">
                    <xsl:for-each select="messages">

                                        <div class="msg justify-content" >
                                            <div>From</div>
                                            <h5 class="alert alert-primary d-flex justify-content-start" style="width:30%">

                                                <xsl:value-of  select="from"/></h5>
                                            <p class="alert-secondary d-flex justify-content-center">
                                                <xsl:value-of select="body"/>
                                            </p>
<!--                                            <div>-->
<!--                                                <div>To</div>-->
<!--                                                <h5 class="alert alert-warning rigth" style="width:30%">-->
<!--                                                    <xsl:value-of select="to"/>-->
<!--                                                </h5>-->
<!--                                            </div>-->


                                        </div>

                        </xsl:for-each>
                </ol>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>