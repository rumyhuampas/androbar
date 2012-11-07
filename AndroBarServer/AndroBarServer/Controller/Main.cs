using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using AndroBarServer.Model;
using System.IO;

namespace AndroBarServer.Controller
{
    public class Main
    {
        private androbarEntities _db;

        public Main()
        {
            _db = new androbarEntities();
        }

        public IQueryable<table> GetTables()
        {
            return from tables in _db.tables
                   select tables;
        }

        public IQueryable<order> GetTableOrders(int tableId)
        {
            return (from orders in _db.orders
                   where orders.TableId == tableId && orders.Status != ABMHelper.OS_CHARGED
                   select orders).OrderBy(o => o.DateTime);
        }

        public IQueryable<dynamic> GetOrderProds(order ord)
        {
            return from op in _db.order_products
                    where op.OrderId == ord.Id
                    group op by op.ProductId into pid
                    join p in _db.products on pid.Key equals p.Id
                    select new { Producto = p.Name, Cantidad = pid.Count() };
        }

        public bool AreAnyReceivedOrders(int tableId)
        {
            IQueryable<order> ords = from orders in _db.orders
                                     where orders.TableId == tableId && orders.Status != ABMHelper.OS_CHARGED
                                     select orders;
            bool found = false;
            foreach (order ord in ords)
            {
                if (ord.Status == ABMHelper.OS_RECEIVED)
                {
                    return true;
                }
            }
            return found;
        }

        public void ChangeOrderStatus(int orderId, string status)
        {
            order ord = (from orders in _db.orders
                         where orders.Id == orderId
                         select orders).First();
            ord.Status = status;
            _db.SaveChanges();
        }
    }
}
