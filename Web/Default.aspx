<%@ Page Language="C#" AutoEventWireup="true"  CodeFile="Default.aspx.cs" Inherits="_Default" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head runat="server">
    <title>Hooly Search</title>
<style><!--
body,td,div,.p,a{font-family:arial,sans-serif }
div,td{color:#000}
.f{color:#6f6f6f}
.flc,.fl:link{color:#77c}
a:link,.w,a.w:link,.w a:link{color:#00c}
a:visited,.fl:visited{color:#551a8b}
a:active,.fl:active{color:#f00}
.t a:link,.t a:active,.t a:visited,.t{color:#000}
.t{background-color:#e5ecf9}
.k{background-color:#36c}
.j{width:34em}
.h{color:#36c}
.i,.i:link{color:#a90a08}
.a,.a:link{color:#008000}
.z{display:none}
div.n{margin-top:1ex}
.n a{font-size:10pt;color:#000}
.n .i{font-size:10pt;font-weight:bold}
.q:visited,.q:link,.q:active,.q{color:#00c;}
.b a{font-size:12pt;color:#00c;font-weight:bold}
.ch{cursor:pointer;cursor:hand}
.sem{display:inline;margin:0;font-size:100%;font-weight:inherit}
.e{margin-top:.75em;margin-bottom:.75em}
.g{margin-top:1em;margin-bottom:1em}
.sm{display:block;margin-top:0px;margin-bottom:0px;margin-left:40px}
-->
</style>    
</head>
<body bgcolor=#ffffff>
    <form id="form1" runat="server">
        <center><a href=Default.aspx><img src="images/Hooly.jpg" border=0></a></center>
        <asp:MultiView ID=mvMain runat=server>
            <asp:View ID=viewQuery runat=server>
                <div align=center>
                <table border=0><tr><td align=center valign=top>
                <asp:TextBox ID=txtPhrase Width=300 runat=server />
                    <br />
                <asp:Button ID=btnSubmit runat=server Text="Search" OnClick="btnSubmit_Click" />
                </td></tr>
                <tr><td align=left valign=top>
                <asp:Literal ID=litDir runat=server />
                </td></tr>
                </table>
                </div>
            </asp:View>
            <asp:View ID=viewResult runat=server>
                <asp:Literal ID=litMain runat=server />
            </asp:View>
        </asp:MultiView>
    </form>
</body>
</html>
