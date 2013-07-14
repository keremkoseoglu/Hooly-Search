using System;
using System.Data;
using System.Configuration;
using System.Collections;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Web.UI.HtmlControls;

public partial class Corrector : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        Folder fol = new Folder(Server.MapPath("Books\\Old.Testament"));
        correctFolder(fol);

    }

    private void correctFolder(Folder F)
    {
        for (int n = 0; n < F.folders.Count; n++)
        {
            Folder f = (Folder)F.folders[n];
            correctFolder(f);
        }

        for (int n = 0; n < F.files.Count; n++)
        {
            File fi = (File)F.files[n];
            ArrayList al = new ArrayList();
            string line = "";
            string newLine = "";
            string ch = "";
            bool numberStarted = true;
            bool sepPassed = false;
            bool numberEnded = false;
            for (int x = 0; x < fi.lines.Count; x++)
            {
                line = fi.lines[x].ToString();
                if (newLine.Trim().Length > 0) newLine += " ";

                for (int y = 0; y < line.Length; y++)
                {
                    ch = line.Substring(y, 1);
                    if (
                        ch == "1" ||
                        ch == "2" ||
                        ch == "3" ||
                        ch == "4" ||
                        ch == "5" ||
                        ch == "6" ||
                        ch == "7" ||
                        ch == "8" ||
                        ch == "9" ||
                        ch == "0"
                        )
                    {
                        if (!numberStarted)
                        {
                            al.Add(newLine);
                            newLine = "";
                            numberStarted = true;
                        }
                    }
                    else
                    {
                        if (ch == ":")
                        {
                            sepPassed = true;
                        }
                        else
                        {
                            if (numberStarted)
                            {
                                numberStarted = false;
                                numberEnded = false;
                                sepPassed = false;
                            }
                        }
                    }

                    newLine += ch;
                }
            }

            if (newLine.Trim().Length > 0) al.Add(newLine);

            fi.lines = al;
            fi.Write();
        }
    }
}
