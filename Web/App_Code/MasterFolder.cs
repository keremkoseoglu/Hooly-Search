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
/// Summary description for MasterFolder
/// </summary>
public class MasterFolder
{
    public ArrayList folders;

	public MasterFolder(string Path)
	{
        folders = new ArrayList();
        DirectoryInfo di = new DirectoryInfo(Path);
        foreach (DirectoryInfo d in di.GetDirectories())
        {
            folders.Add(new Folder(d.FullName));
        }
	}
}
