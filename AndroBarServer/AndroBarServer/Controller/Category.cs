﻿using System;
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

        public bool SaveCategory(ABMHelper.ABMMODE mode, int id, Stream image, string name)
        {
            name = name.Trim();
            category c = null;
            if (mode == ABMHelper.ABMMODE.ABMMODE_NEW)
            {
                c = new category();
            }
            else
            {
                c = ABMHelper.GetCategory(id);
            }
            c.Name = name;
            c.Image = ABMHelper.StreamToByteArray(image, 1024);
            _db.AddTocategories(c);
            return _db.SaveChanges() > 0;
        }

        public IEnumerable<dynamic> GetAllCategories()
        {
            return GetData("");
        }

        public IEnumerable<dynamic> GetData(string filter)
        {
            return from cat in _db.categories
                   where cat.Name.Contains(filter)
                   select new { cat.Id, cat.Name };
        }
    }
}