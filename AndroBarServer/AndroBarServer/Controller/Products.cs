using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using AndroBarServer.Model;

namespace AndroBarServer.Controller
{
    public class Products
    {
        private androbarEntities _db;

        public Products()
        {
            _db = new androbarEntities();
        }

        public IEnumerable<dynamic> GetAllProducts()
        {
            return GetData("");
        }

        public IEnumerable<dynamic> GetData(string filter)
        {
            return from prod in _db.products
                   where prod.Name.Contains(filter)
                   select new { prod.Id, prod.Name, prod.Price, prod.CostPrice };
        }

        public void DeleteProduct(int id)
        {
            product p = ABMHelper.GetProduct(_db, id);
            _db.DeleteObject(p);
            _db.SaveChanges();
        }
    }
}
