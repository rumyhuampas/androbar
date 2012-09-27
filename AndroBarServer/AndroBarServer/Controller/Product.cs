using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using AndroBarServer.Model;

namespace AndroBarServer.Controller
{
    public class Product
    {
        private androbarEntities _db;

        public Product()
        {
            _db = new androbarEntities();
        }
    }
}
