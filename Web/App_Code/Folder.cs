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
/// Summary description for Folder
/// </summary>
public class Folder
{
    public string Name;
    public string CoolName;
    public ArrayList folders;
    public ArrayList files;

    public Folder(string Path)
    {
        files = new ArrayList();
        folders = new ArrayList();
        DirectoryInfo di = new DirectoryInfo(Path);
        Name = di.Name;

        CoolName = getCoolName(di.FullName);

        foreach (DirectoryInfo di2 in di.GetDirectories())
        {
            folders.Add(new Folder(di2.FullName));
        }


        foreach (FileInfo fi in di.GetFiles())
        {
            files.Add(new File(fi.FullName));
        }

    }

    private string getCoolName(string Name)
    {
        string dir = Name;
        int i = dir.IndexOf("books");
        if (dir.Length - i - 6 > i + 6)
        {
            dir = dir.Substring(i + 6, dir.Length - i - 6);
        }
        dir = dir.Replace("\\", " :: ");
        return dir;
    }
}
