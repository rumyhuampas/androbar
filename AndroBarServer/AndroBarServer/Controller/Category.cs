using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using AndroBarServer.Model;
using System.IO;

namespace AndroBarServer.Controller
{
    public class Category
    {
        private androbarEntities _db;

        public Category()
        {
            _db = new androbarEntities();
        }

        public bool SaveCategory(int id, Stream image, string name)
        {
            name = name.Trim();
            category c = null;
            if (id == -1)
            {
                c = new category();
                c.Name = name;
                if (image != null)
                {
                    c.Image = ABMHelper.StreamToByteArray(image, 1024);
                }
                _db.AddTocategories(c);
            }
            else
            {
                c = ABMHelper.GetCategory(_db, id);
                c.Name = name;
                if (image != null)
                {
                    c.Image = ABMHelper.StreamToByteArray(image, 1024);
                }
            }
            return _db.SaveChanges() > 0;
        }
    }
}
