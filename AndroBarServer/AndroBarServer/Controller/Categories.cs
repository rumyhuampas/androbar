using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using AndroBarServer.Model;

namespace AndroBarServer.Controller
{
    public class Categories
    {
        private androbarEntities _db;

        public Categories()
        {
            _db = new androbarEntities();
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
