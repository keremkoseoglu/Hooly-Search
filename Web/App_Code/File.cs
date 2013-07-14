using System;
using System.Data;
using System.Collections;
using System.Configuration;
using System.IO;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Web.UI.HtmlControls;

/// <summary>
/// Summary description for File
/// </summary>
public class File
{
    public string Name;
    public string ShortName;
    public string CoolName;
    public ArrayList lines;
    public ArrayList results;
    public ArrayList SearchWords;

    public enum DISPLAY_STYLE : int { COOL, NORMAL, SHORT };

    public File(string Path)
    {
        Name = Path;
        ShortName = getShortName();
        CoolName = getCoolName();
        lines = new ArrayList();

        string input = null;
        StreamReader re = new StreamReader(Path, System.Text.Encoding.GetEncoding("windows-1254"));
        while ((input = re.ReadLine()) != null)
        {
            lines.Add(input);
        }
        re.Close();
    }

    private string getShortName()
    {
        string ret = "";
        for (int n = 0; n < Name.Length; n++)
        {
            string ch = Name.Substring(n, 1);
            if (ch == "\\") ret = ""; else ret += ch;
        }

        ret = ret.Replace(".txt", "");
        ret = ret.Replace(".TXT", "");
        return ret;
    }

    private string getCoolName()
    {
        string dir = Name;
        dir = dir.Substring(dir.IndexOf("books") + 6, dir.Length - dir.IndexOf("books") - 10);
        dir = dir.Replace("\\", " :: ");
        return dir;
    }

    public static string convertCoolNameToPath(string CoolName)
    {
        string p = "books\\" + CoolName;
        p = p.Replace(" :: ", "\\");
        p += ".txt";
        return p;
    }

    public static string getFolderName(string Name)
    {
        string cha = "";
        string cur = "";
        string ret = "";
        for (int n = 0; n < Name.Length; n++)
        {
            cha = Name.Substring(n, 1);
            switch (cha)
            {
                case "\\":
                    ret += cur + "\\";
                    cur = "";
                    break;
                default:
                    cur += cha;
                    break;
            }
        }

        return ret;
    }

        public void Write()
    {
        StreamWriter wr = new StreamWriter(Name, false, System.Text.Encoding.GetEncoding("windows-1254"));
        for (int n = 0; n < lines.Count; n++)
        {
            wr.WriteLine(lines[n].ToString());
        }
        wr.Close();
    }

    public void Search(string Phrase)
    {
        SearchWords = new ArrayList();
        results = new ArrayList();

        string word = "";
        string letter = "";
        for (int n = 0; n < Phrase.Length; n++)
        {
            letter = Phrase.Substring(n, 1);
            switch (letter)
            {
                case " ":
                    SearchWords.Add(word);
                    word = "";
                    break;
                default:
                    word += letter;
                    break;
            }
        }
        if (word != "") SearchWords.Add(word);

        for (int n = 0; n < lines.Count; n++)
        {
            for (int x = 0; x < SearchWords.Count; x++)
            {
                string s = lines[n].ToString();
                string z = SearchWords[x].ToString().ToUpper();
                if (s.ToUpper().IndexOf(z) >= 0)
                {
                    results.Add(lines[n].ToString());
                }
            }
        }
    }

    public int getIndexOfSure(string X)
    {
        string word = "";
        for (int n = 0; n < X.Length; n++)
        {
            string letter = X.Substring(n, 1);
            if (letter == "0" ||
                letter == "1" ||
                letter == "2" ||
                letter == "3" ||
                letter == "4" ||
                letter == "5" ||
                letter == "6" ||
                letter == "7" ||
                letter == "8" ||
                letter == "9")
            {
                word += letter;
            }
            else
            {
                n = X.Length + 1;
            }
        }

        int x = Convert.ToInt32(word);
        x--;
        return x;
    }
}
