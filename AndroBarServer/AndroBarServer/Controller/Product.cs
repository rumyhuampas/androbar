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

        public IEnumerable<dynamic> GetAllCategories()
        {
            return from cat in _db.categories
                   select new { cat.Id, cat.Name };
        }

        public IEnumerable<dynamic> GetProdCategories(int prodId)
        {
            return from catprod in _db.category_products 
                   where catprod.ProductId == prodId
                   select new { catprod.category.Id, catprod.category.Name };
        }

        public void DeleteProductCategory(int prodId, int catId)
        {
            category_products cp = (from catprod in _db.category_products
                                   where catprod.CategoryId == catId && catprod.ProductId == prodId
                                   select catprod).First();
            _db.category_products.DeleteObject(cp);
            _db.SaveChanges();
        }
    }
}
