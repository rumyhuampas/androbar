using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using AndroBarServer.Model;
using System.Collections;
using System.IO;

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

        public void DeleteProductCategoryRelations(int prodId)
        {
            IEnumerable<category_products> cps = from catprod in _db.category_products
                                   where catprod.ProductId == prodId
                                   select catprod;
            foreach (category_products cp in cps)
            {
                _db.category_products.DeleteObject(cp);
            }
            _db.SaveChanges();
        }

        public int SaveProduct(int id, string name, string desc, string price, string costPrice, Stream image)
        {
            name = name.Trim();
            desc = desc.Trim();
            price = price.Trim();
            costPrice = costPrice.Trim();
            product p = null;
            if (id == -1)
            {
                p = new product();
                p.Name = name;
                p.Description = desc;
                p.Price = float.Parse(price);
                p.CostPrice = float.Parse(costPrice);
                if (image != null)
                {
                    p.Image = ABMHelper.StreamToByteArray(image, 1024);
                }
                _db.AddToproducts(p);
            }
            else
            {
                p = ABMHelper.GetProduct(_db, id);
                p.Name = name;
                p.Description = desc;
                p.Price = float.Parse(price);
                p.CostPrice = float.Parse(costPrice);
                if (image != null)
                {
                    p.Image = ABMHelper.StreamToByteArray(image, 1024);
                }
            }
            _db.SaveChanges();
            return p.Id;
        }

        public void SaveProductCategoyRelations(int prodId, List<int> catIds)
        {
            foreach (int catId in catIds)
            {
                category_products cp = new category_products();
                cp.CategoryId = catId;
                cp.ProductId = prodId;
                _db.AddTocategory_products(cp);
            }
            _db.SaveChanges();
        }
    }
}
