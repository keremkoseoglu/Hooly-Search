using System;
using System.Data;
using System.Configuration;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Web.UI.HtmlControls;

public partial class _Default : System.Web.UI.Page 
{
    public static Folder mf;
    public static File index;

    private string lastBigName;
    private int folderTreeDepth;

    protected void Page_Load(object sender, EventArgs e)
    {
        litMain.Text = "";

        try
        {
            string a = Request.QueryString["a"].ToString();

            switch (a)
            {
                case "dp":
                    try
                    {
                        string p = Request.QueryString["p"].ToString();
                        p = File.convertCoolNameToPath(p);
                        File fi = new File(Server.MapPath(p));
                        paintFile(fi);
                        mvMain.ActiveViewIndex = 1;
                        return;
                    }
                    catch (Exception ex)
                    {
                    }
                    break;
                case "db":
                    try
                    {
                        string b = Request.QueryString["b"].ToString();
                        b = File.convertCoolNameToPath(b);
                        b = File.getFolderName(b);
                        Folder fo = new Folder(Server.MapPath(b));

                        string s = litMain.Text;
                        paintFolder(fo, ref s);
                        litMain.Text = s;

                        mvMain.ActiveViewIndex = 1;
                        return;
                    }
                    catch (Exception ex)
                    {
                    }
                    break;
                case "s":
                    try
                    {
                        string r = "";
                        searchFolder(mf, Request.QueryString["t"].ToString(), ref r);
                        litMain.Text = r;
                        mvMain.ActiveViewIndex = 1;
                        return;
                    }
                    catch
                    {
                    }
                    break;
            }

        }
        catch(Exception ex)
        {
            mvMain.ActiveViewIndex = 0;
            mf = new Folder(Server.MapPath("books"));
            litDir.Text = "";
            string s = litDir.Text;
            folderTreeDepth = 0;
            paintFolder(mf, ref s, File.DISPLAY_STYLE.SHORT, ", ");
            litDir.Text = s;
        }

    }

    private void paintFolder(Folder fo, ref string S)
    {
        paintFolder(fo, ref S, File.DISPLAY_STYLE.COOL, "<br>");
    }

    private void paintFolder(Folder fo, ref string S, File.DISPLAY_STYLE DS, string FileSeparator)
    {
        folderTreeDepth++;

        S += "<blockquote>";
        S += "<b>";
        for (int n = 0; n < folderTreeDepth - 1; n++)
        {
            S += ".";
        }
        S += fo.Name + "</b><br><br>";
        for (int n = 0; n < fo.files.Count; n++)
        {
            File fi = (File)fo.files[n];
            S += prepareFileLink(fi, "fl");
            switch (DS)
            {
                case File.DISPLAY_STYLE.COOL:
                    S += fi.CoolName;
                    break;
                case File.DISPLAY_STYLE.NORMAL:
                    S += fi.Name;
                    break;
                case File.DISPLAY_STYLE.SHORT:
                    S += fi.ShortName;
                    break;
            }
            S += "</a>";
            if (n < fo.files.Count - 1) S += FileSeparator;
        }
        for (int n = 0; n < fo.folders.Count; n++)
        {
            Folder sfo = (Folder)fo.folders[n];
            paintFolder(sfo, ref S, DS, FileSeparator);
        }
        S += "</blockquote>";

        folderTreeDepth--;
    }

    private void paintFile(File fi)
    {
        string s = litMain.Text;
        paintFile(fi, ref s);
        litMain.Text = s;
    }

    private void paintFile(File fi, ref string S)
    {
        S += "<b>" + fi.CoolName + "</b><br><br>";
        for (int n = 0; n < fi.lines.Count; n++)
        {
            S += fi.lines[n].ToString() + "<br><br>";
        }
    }

    protected void btnSubmit_Click(object sender, EventArgs e)
    {
        Response.Redirect("default.aspx?a=s&t=" + txtPhrase.Text, true);
    }

    private void searchFolder(Folder F, string Phrase, ref string S)
    {

        for (int d = 0; d < F.folders.Count; d++)
        {
            Folder fo = (Folder)F.folders[d];
            searchFolder(fo, Phrase, ref S);

            for (int f = 0; f < fo.files.Count; f++)
            {
                File fi = (File)fo.files[f];
                fi.Search(Phrase);
                for (int r = 0; r < fi.results.Count; r++)
                {
                    string bigName = fo.Name + " - " + fi.ShortName;
                    if (bigName != lastBigName)
                    {
                        if (r > 0) S += "</p></div>";
                        S += "<div><p class=g>" + prepareFileLink(fi, "l") + "<b>";
                        S += fi.CoolName;
                        S += "</b></a><br>";
                    }
                    S += "<table cellpadding=0 cellspacing=0 border=0><tr><td class=j><font size=-1>";
                    string x = fi.results[r].ToString();
                    for (int n = 0; n < fi.SearchWords.Count; n++) x = x.Replace(fi.SearchWords[n].ToString(), "<b>" + fi.SearchWords[n].ToString() + "</b>");
                    S += "<big>" + x + "</big>";
                    S += "<br><font color=#008000>" + bigName + " - </font><nobr>";
                    S += prepareFileLink(fi, "fl") + "Display Section</a> - " + prepareFolderLink(fi, "fl") + "Browse Book</a></nobr></font></td></tr></table>";
                    S += "<br>";

                    lastBigName = bigName;
                }
            }
        }

    }

    private string prepareFileLink(File fi, string Class)
    {
        return "<a class=" + Class + " href='default.aspx?a=dp&p=" + fi.CoolName + "&now=" + System.DateTime.Now + "'>";
    }

    private string prepareFolderLink(File fi, string Class)
    {
        return "<a class=" + Class + " href='default.aspx?a=db&b=" + fi.CoolName + "&now=" + System.DateTime.Now + "'>";
    }

    private string prepareFolderLink(Folder fo, string Class)
    {
        return "<a class=" + Class + " href='default.aspx?a=db&b=" + fo.Name + " :: dummy&now=" + System.DateTime.Now + "'>";
    }

}
