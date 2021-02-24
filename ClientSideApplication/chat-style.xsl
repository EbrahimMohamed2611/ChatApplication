<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
    <xsl:output method="html"/>
    <xsl:template match="/*">
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
            </head>
            <body>

                <ol class="chat">
                    <xsl:for-each select="messages">
<!--                        <div class="menu">-->
<!--                            <div class="logo"><p>Chat session on <xsl:value-of select="SessionDate"/></p></div>-->
<!--                        </div>-->
                        <xsl:for-each select="messages">
                            <xsl:choose>
                                <xsl:when test="@pos='left'">
                                    <li class="self">
                                        <div class="msg" >
                                            <h2> <xsl:value-of select="from"/></h2>
                                            <p>

                                                <xsl:value-of select="body"/>
                                            </p>
                                        </div>
                                    </li>
                                </xsl:when>
                                <xsl:otherwise>
                                    <li class="other">
                                        <div class="msg" >
                                            <h2> <xsl:value-of select="from"/></h2>
                                            <p>

                                                <xsl:value-of select="body"/>
                                            </p>
                                        </div>
                                    </li>
                                </xsl:otherwise>
                            </xsl:choose>
                        </xsl:for-each>
                    </xsl:for-each>
                </ol>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>